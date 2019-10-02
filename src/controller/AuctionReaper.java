package controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import controller.database.ResultDatabase;
import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.select.SelectComponent;
import controller.database.select.SimpleSelect;
import controller.database.select.decorator.Where;
import exception.FailRollBackException;
import exception.InexistentTypeParameterException;
import exception.SQLiteFailRequestException;
import model.auction.Auction;

public class AuctionReaper {

	private static AuctionReaper instance = null;
	private static final int REFRESH_TIME_LAPSE = 1; //this is in hour
	private LocalDateTime launchedRefresherTime;
	private LocalDateTime endLapse;
	
	private AuctionReaper() {
		prepareTask();
	}
	
	public static AuctionReaper getInstance() {
		if(instance == null) {
			instance = new AuctionReaper();
		}
		return instance;
	}

	protected void prepareTask() {
		calculateTimeTask();
		Timer timerRefresher = new Timer();
		timerRefresher.schedule(new RefreshTask(), Date.from(endLapse.atZone(ZoneId.systemDefault()).toInstant()));
		
		createTimer();		
		
		closeOldAuctions();
	}
	
	

	private void createTimer() {
		SelectComponent select = new SimpleSelect("closingAuction", endLapse);
		select = new Where(select, "AND Conclusion >= ?", new SQLParameter(SQLParameter.DATE_TIME, launchedRefresherTime));
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
	
	private void closeOldAuctions() {
		SelectComponent select = new SimpleSelect("closingAuction", launchedRefresherTime);
		
		try {
			ResultDatabase auctions = DatabaseManager.executeSelect(select);
			for(int index = 0; index < auctions.size(); index++) {
				
				Auction oldAuction = AuctionFactory.getAuctionFromValues(auctions.getRowValues(index));
				DatabaseManager.execute(oldAuction.getCloseOperation());
				
			}
		} catch (SQLiteFailRequestException | InexistentTypeParameterException | FailRollBackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void calculateTimeTask() {
		launchedRefresherTime = LocalDateTime.now();
		
		endLapse = launchedRefresherTime.plusHours(REFRESH_TIME_LAPSE);
		endLapse = endLapse.plusMinutes(- endLapse.getMinute());
		endLapse = endLapse.plusSeconds(- endLapse.getSecond());
		
	}
	
	private class RefreshTask extends TimerTask{

		@Override
		public void run() {
			AuctionReaper.getInstance().prepareTask();
			
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

