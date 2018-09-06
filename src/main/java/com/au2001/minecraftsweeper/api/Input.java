package com.au2001.minecraftsweeper.api;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CancellationException;

public abstract class Input {

	protected final List<Output> outputs;

	public Input() {
		this(null);
	}

	public Input(Set<Output> outputs) {
		this.outputs = outputs != null? new LinkedList<Output>(outputs) : new LinkedList<Output>();
	}

	public void sweep(Square square) {
		try {
			for (Output output : this.outputs)
				if (output != null) output.sweep(square);
		} catch (CancellationException e) {}
	}

	public void rollback(Square square) {
		try {
			for (Output output : this.outputs)
				if (output != null) output.rollback(square);
		} catch (CancellationException e) {}
	}

	public void flag(Square square) {
		try {
			for (Output output : this.outputs)
				if (output != null) output.flag(square);
		} catch (CancellationException e) {}
	}

	public void unflag(Square square) {
		try {
			for (Output output : this.outputs)
				if (output != null) output.unflag(square);
		} catch (CancellationException e) {}
	}

	public List<Output> getOutputs() {
		return Collections.unmodifiableList(outputs);
	}

	public void addOutput(Output output) {
		this.outputs.add(output);
	}

	public void removeOutput(Output output) {
		this.outputs.remove(output);
		if (output instanceof Closeable) {
			try {
				((Closeable) output).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void clearOutputs() {
		for (Output output : new LinkedHashSet<Output>(this.outputs))
			this.removeOutput(output);
	}

}
