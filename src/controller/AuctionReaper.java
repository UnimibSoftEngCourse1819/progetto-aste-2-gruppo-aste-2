package controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import controller.database.ResultDatabase;
import controller.database.SQLOperation;
import controller.database.select.SelectComponent;
import controller.database.select.SimpleSelect;
import exception.FailRollBackException;
import exception.InexistentTypeParameterException;
import exception.SQLiteFailRequestException;
import model.auction.Auction;

public class AuctionReaper {

	private static AuctionReaper instance = null;
	private static final int REFRESH_TIME_LAPSE = 1; //this is in hour
	private LocalDateTime startLapse;
	private LocalDateTime endLapse;
	
	private AuctionReaper() {
		start();
	}
	
	public static AuctionReaper getInstance() {
		if(instance == null) {
			instance = new AuctionReaper();
		}
		return instance;
	}
	
	private void start() {
		Timer timer = new Timer();
		timer.schedule(new RefreshTask(), getTimeToSchedule());
		
	}

	protected void refresh() {
		SelectComponent select = new SimpleSelect("closingAuction", startLapse, endLapse);
		try {
			ResultDatabase auctions = DatabaseManager.executeSelect(select);
			for(int index = 0; index < auctions.size(); index++) {
				Timer timer = new Timer();
				
				LocalDateTime timeEnd = (LocalDateTime) auctions.getValue("Conclusion", index);
				TimerTask endTask = new ClosingTask(AuctionFactory.getAuctionFromValues(auctions.getRowValues(index)));
				
				timer.schedule(endTask, Date.from(timeEnd.atZone(ZoneId.systemDefault()).toInstant()));
			}
		} catch (SQLiteFailRequestException | InexistentTypeParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		
		
		
	}
	
	/**
	 * Example 17:12 45 s -> 18:00 0 s
	 * 
	 * @return
	 */
	private Date getTimeToSchedule() {
		LocalDateTime currentTime = LocalDateTime.now();
		currentTime = currentTime.plusHours(REFRESH_TIME_LAPSE);
		currentTime = currentTime.plusMinutes(- currentTime.getMinute());
		currentTime = currentTime.plusSeconds(- currentTime.getSecond());
		
		return Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	
	private class RefreshTask extends TimerTask{

		@Override
		public void run() {
			AuctionReaper.getInstance().refresh();
			
		}
		
	}
	
	/**
	 * Strategy Design Pattern applied
	 * 
	 * @author Vallero
	 *
	 */
	private class ClosingTask extends TimerTask{
		private Auction strategy;

		private ClosingTask(Auction strategy) {
			this.strategy = strategy;
		}
		
		@Override
		public void run() {
			List<SQLOperation> operation = strategy.getCloseOperation();
			try {
				DatabaseManager.execute(operation);
			} catch (SQLiteFailRequestException | FailRollBackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}

