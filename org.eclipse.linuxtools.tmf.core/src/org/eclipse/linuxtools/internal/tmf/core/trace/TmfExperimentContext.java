/*******************************************************************************
 * Copyright (c) 2009, 2010, 2012 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Francois Chouinard - Initial API and implementation
 * Francois Chouinard - Put in shape for 1.0
 *******************************************************************************/

package org.eclipse.linuxtools.internal.tmf.core.trace;

import org.eclipse.linuxtools.tmf.core.event.ITmfEvent;
import org.eclipse.linuxtools.tmf.core.trace.ITmfContext;
import org.eclipse.linuxtools.tmf.core.trace.ITmfLocation;
import org.eclipse.linuxtools.tmf.core.trace.TmfContext;

/**
 * The experiment context in TMF.
 * <p>
 * The experiment keeps track of the next event from each of its traces so it
 * can pick the next one in chronological order.
 * <p>
 * This implies that the "next" event from each trace has already been
 * read and that we at least know its timestamp. This doesn't imply that a
 * full parse of the event content was performed (read: the legacy LTTng works
 * like this...).
 * <p>
 * The last trace refers to the trace from which the last event was "consumed"
 * at the experiment level.
 */
public class TmfExperimentContext extends TmfContext implements Cloneable {

    // ------------------------------------------------------------------------
    // Constants
    // ------------------------------------------------------------------------

    /**
     * No last trace read indicator
     */
    public static final int NO_TRACE = -1;

    // ------------------------------------------------------------------------
    // Attributes
    // ------------------------------------------------------------------------

    private ITmfContext[] fContexts;
    private ITmfEvent[] fEvents;
    private int fLastTraceRead;

    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------

    public TmfExperimentContext(final ITmfContext[] contexts) {
        super();
        fContexts = contexts;
        fEvents = new ITmfEvent[fContexts.length];
        final ITmfLocation<?>[] locations = new ITmfLocation[fContexts.length];
        final long[] ranks = new long[fContexts.length];
        long rank = 0;
        for (int i = 0; i < fContexts.length; i++)
            if (contexts[i] != null) {
                locations[i] = contexts[i].getLocation();
                ranks[i] = contexts[i].getRank();
                rank += contexts[i].getRank();
            }

        setLocation(new TmfExperimentLocation(new TmfLocationArray(locations)));
        setRank(rank);
        fLastTraceRead = NO_TRACE;
    }

    public TmfExperimentContext(final TmfExperimentContext other) {
        this(other.cloneContexts());
        fEvents = other.fEvents;
        if (other.getLocation() != null)
            setLocation(other.getLocation().clone());
        setRank(other.getRank());
        setLastTrace(other.fLastTraceRead);
    }

    private ITmfContext[] cloneContexts() {
        final ITmfContext[] contexts = new ITmfContext[fContexts.length];
        for (int i = 0; i < fContexts.length; i++)
            contexts[i] = fContexts[i].clone();
        return contexts;
    }

    private ITmfEvent[] cloneEvents() {
        final ITmfEvent[] events = new ITmfEvent[fEvents.length];
        for (int i = 0; i < fEvents.length; i++)
            events[i] = fEvents[i].clone();
        return events;
    }

    @Override
    public TmfExperimentContext clone2() {
        TmfExperimentContext clone = null;
        clone = (TmfExperimentContext) super.clone();
        clone.fContexts = cloneContexts();
        clone.fEvents = cloneEvents();
        clone.fLastTraceRead = NO_TRACE;
        return clone;
    }

    // ------------------------------------------------------------------------
    // Accessors
    // ------------------------------------------------------------------------

    public ITmfContext[] getContexts() {
        return fContexts;
    }

    public ITmfEvent[] getEvents() {
        return fEvents;
    }

    public int getLastTrace() {
        return fLastTraceRead;
    }

    public void setLastTrace(final int newIndex) {
        fLastTraceRead = newIndex;
    }

    // ------------------------------------------------------------------------
    // Object
    // ------------------------------------------------------------------------

    @Override
    public int hashCode() {
        int result = 17;
        for (int i = 0; i < fContexts.length; i++) {
            result = 37 * result + fContexts[i].hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (!(other instanceof TmfExperimentContext))
            return false;
        final TmfExperimentContext o = (TmfExperimentContext) other;
        boolean isEqual = true;
        int i = 0;
        while (isEqual && (i < fContexts.length)) {
            isEqual &= fContexts[i].equals(o.fContexts[i]);
            i++;
        }
        return isEqual;
    }

}
