package com.au2001.minecraftsweeper.api.storage;

import java.io.*;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashSet;
import java.util.Map.Entry;

public class BinaryGameStorage extends TempGameStorage {

	protected static final int BUFFER_TIME = 3 * 1000;

	protected final File file;
	protected Thread thread = null;

	public BinaryGameStorage(File file) {
		super();

		this.file = file;

		this.load();
	}

	private void load() {
		try {
			FileInputStream fileOut = new FileInputStream(this.file);
			ObjectInputStream out = new ObjectInputStream(fileOut);
			this.resetCount.set(out.readLong());

			int sweepStorageSize = out.readInt();
			for (int i = 0; i < sweepStorageSize; i++) {
				int x = out.readInt(), y = out.readInt();
				Entry<Integer, Integer> entry = new SimpleImmutableEntry<Integer, Integer>(x, y);
				this.sweepStorage.add(entry);
			}

			int rollbackStorageSize = out.readInt();
			for (int i = 0; i < rollbackStorageSize; i++) {
				int x = out.readInt(), y = out.readInt();
				long count = out.readLong();
				Entry<Integer, Integer> entry = new SimpleImmutableEntry<Integer, Integer>(x, y);
				this.rollbackStorage.put(entry, count);
			}

			out.close();
		} catch (FileNotFoundException e) {
			this.resetCount.set(0);
			this.sweepStorage.clear();
			this.rollbackStorage.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void save() {
		if (this.thread != null) return;
		this.thread = new Thread() {
			public void run() {
				try {
					Thread.sleep(BUFFER_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					if (!BinaryGameStorage.this.file.isFile()) {
						BinaryGameStorage.this.file.getParentFile().mkdirs();
						BinaryGameStorage.this.file.createNewFile();
					}

					FileOutputStream fileOut = new FileOutputStream(BinaryGameStorage.this.file);
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeLong(BinaryGameStorage.this.resetCount.longValue());

					out.writeInt(BinaryGameStorage.this.sweepStorage.size());
					for (Entry<Integer, Integer> entry : new HashSet<Entry<Integer, Integer>>(BinaryGameStorage.this.sweepStorage)) {
						out.writeInt(entry.getKey());
						out.writeInt(entry.getValue());
					}

					out.writeInt(BinaryGameStorage.this.rollbackStorage.size());
					for (Entry<Entry<Integer, Integer>, Long> entry : new HashSet<Entry<Entry<Integer, Integer>, Long>>(BinaryGameStorage.this.rollbackStorage.entrySet())) {
						out.writeInt(entry.getKey().getKey());
						out.writeInt(entry.getKey().getValue());
						out.writeLong(entry.getValue());
					}

					out.close();
				} catch (FileNotFoundException e) {
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					BinaryGameStorage.this.thread = null;
				}
			}
		};
		this.thread.start();
	}

	@Override
	public void setResetCount(long count) {
		super.setResetCount(count);
		this.save();
	}

	@Override
	public void setSweeped(int x, int y, boolean sweeped) {
		super.setSweeped(x, y, sweeped);
		this.save();
	}

	@Override
	public void clearSweeped() {
		super.clearSweeped();
		this.save();
	}

	@Override
	public void setRollbackCount(int x, int y, long count) {
		super.setRollbackCount(x, y, count);
		this.save();
	}

}
