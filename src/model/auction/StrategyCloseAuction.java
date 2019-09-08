package model.auction;

import java.util.List;

import jdk.nashorn.internal.objects.annotations.Function;
import model.Offer;
/**
 * This may be useless 
 * @author Vallero
 *
 */
public interface StrategyCloseAuction {
	@Function
	public abstract DataForTransiction getWinner(List<Offer> offers);
}
