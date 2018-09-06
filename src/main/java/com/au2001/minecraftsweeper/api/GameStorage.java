package com.au2001.minecraftsweeper.api;

import java.util.Map.Entry;
import java.util.Set;

public interface GameStorage {

	public long getResetCount();
	public void setResetCount(long count);

	public boolean isSweeped(int x, int y);
	public void setSweeped(int x, int y, boolean sweeped);
	public Set<Entry<Integer, Integer>> getSweeped();
	public void clearSweeped();

	public boolean isFlagged(int x, int y);
	public void setFlagged(int x, int y, boolean sweeped);
	public Set<Entry<Integer, Integer>> getFlagged();
	public void clearFlagged();

	public long getRollbackCount(int x, int y);
	public void setRollbackCount(int x, int y, long count);

	public long getTotal(int x, int y);

}
