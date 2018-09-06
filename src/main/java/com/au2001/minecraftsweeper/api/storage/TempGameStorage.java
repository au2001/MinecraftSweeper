package com.au2001.minecraftsweeper.api.storage;

import com.au2001.minecraftsweeper.api.GameStorage;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

public class TempGameStorage implements GameStorage {

	protected final AtomicLong resetCount;
	protected final Set<Entry<Integer, Integer>> sweepStorage;
	protected final Set<Entry<Integer, Integer>> flagStorage;
	protected final Map<Entry<Integer, Integer>, Long> rollbackStorage;

	public TempGameStorage() {
		this.resetCount = new AtomicLong(0);
		this.sweepStorage = new HashSet<Entry<Integer, Integer>>();
		this.flagStorage = new HashSet<Entry<Integer, Integer>>();
		this.rollbackStorage = new HashMap<Entry<Integer, Integer>, Long>();
	}

	@Override
	public long getResetCount() {
		return this.resetCount.get();
	}

	@Override
	public void setResetCount(long count) {
		this.resetCount.set(count);
	}

	@Override
	public boolean isSweeped(int x, int y) {
		Entry<Integer, Integer> entry = new SimpleImmutableEntry<Integer, Integer>(x, y);
		return this.sweepStorage.contains(entry);
	}

	@Override
	public void setSweeped(int x, int y, boolean sweeped) {
		Entry<Integer, Integer> entry = new SimpleImmutableEntry<Integer, Integer>(x, y);
		if (sweeped) this.sweepStorage.add(entry);
		else this.sweepStorage.remove(entry);
	}

	@Override
	public Set<Entry<Integer, Integer>> getSweeped() {
		Set<Entry<Integer, Integer>> sweepStorage = new HashSet<Entry<Integer, Integer>>(this.sweepStorage);
		return Collections.unmodifiableSet(sweepStorage);
	}

	@Override
	public void clearSweeped() {
		this.sweepStorage.clear();
	}

	@Override
	public boolean isFlagged(int x, int y) {
		Entry<Integer, Integer> entry = new SimpleImmutableEntry<Integer, Integer>(x, y);
		return this.flagStorage.contains(entry);
	}

	@Override
	public void setFlagged(int x, int y, boolean flagged) {
		Entry<Integer, Integer> entry = new SimpleImmutableEntry<Integer, Integer>(x, y);
		if (flagged) this.flagStorage.add(entry);
		else this.flagStorage.remove(entry);
	}

	@Override
	public Set<Entry<Integer, Integer>> getFlagged() {
		Set<Entry<Integer, Integer>> flagStorage = new HashSet<Entry<Integer, Integer>>(this.flagStorage);
		return Collections.unmodifiableSet(flagStorage);
	}

	@Override
	public void clearFlagged() {
		this.flagStorage.clear();
	}

	@Override
	public long getRollbackCount(int x, int y) {
		Entry<Integer, Integer> entry = new SimpleImmutableEntry<Integer, Integer>(x, y);
		Long rollbackCount = this.rollbackStorage.get(entry);
		return rollbackCount != null? rollbackCount.longValue() : 0;
	}

	@Override
	public void setRollbackCount(int x, int y, long count) {
		Entry<Integer, Integer> entry = new SimpleImmutableEntry<Integer, Integer>(x, y);
		if (count == 0) this.rollbackStorage.remove(entry);
		else this.rollbackStorage.put(entry, Long.valueOf(count));
	}

	@Override
	public long getTotal(int x, int y) {
		return this.getResetCount() + this.getRollbackCount(x, y);
	}

}
