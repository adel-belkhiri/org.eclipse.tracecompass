/**********************************************************************
 * Copyright (c) 2012 Ericsson
 * 
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Bernd Hufmann - Initial API and implementation
 **********************************************************************/
package org.eclipse.linuxtools.lttng.ui.views.control.model.impl;

import org.eclipse.linuxtools.lttng.ui.views.control.model.IBaseEventInfo;
import org.eclipse.linuxtools.lttng.ui.views.control.model.TraceEventType;
import org.eclipse.linuxtools.lttng.ui.views.control.model.TraceLogLevel;

/**
* <b><u>BaseEventInfo</u></b>
* <p>
* Implementation of the basic trace event interface (IEventInfo) to store event
* related data. 
* </p>
*/
public class BaseEventInfo extends TraceInfo implements IBaseEventInfo {

    // ------------------------------------------------------------------------
    // Attributes
    // ------------------------------------------------------------------------
    /**
     * The trace event type.
     */
    private TraceEventType fEventType = TraceEventType.UNKNOWN;
    /**
     * The trace log level.
     */
    private TraceLogLevel fLogLevel = TraceLogLevel.TRACE_DEFAULT;
  
    // ------------------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------------------
    /**
     * Constructor
     * @param name - name of base event
     */
    public BaseEventInfo(String name) {
        super(name);
    }

    /**
     * Copy constructor
     * @param other - the instance to copy
     */
    public BaseEventInfo(BaseEventInfo other) {
        super(other);
        fEventType = other.fEventType;
    }

    // ------------------------------------------------------------------------
    // Accessors
    // ------------------------------------------------------------------------
    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.model.IBaseEventInfo#getEventType()
     */
    @Override
    public TraceEventType getEventType() {
        return fEventType;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.model.IBaseEventInfo#setEventType(org.eclipse.linuxtools.lttng.ui.views.control.model.TraceEventType)
     */
    @Override
    public void setEventType(TraceEventType type) {
        fEventType = type;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.model.IBaseEventInfo#setEventType(java.lang.String)
     */
    @Override
    public void setEventType(String typeName) {
        if(TraceEventType.TRACEPOINT.getInName().equals(typeName)) {
            fEventType = TraceEventType.TRACEPOINT;
        } else {
            fEventType = TraceEventType.UNKNOWN;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.model.IBaseEventInfo#getLogLevel()
     */
    @Override
    public TraceLogLevel getLogLevel() {
        return fLogLevel;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.model.IBaseEventInfo#setLogLevel(org.eclipse.linuxtools.lttng.ui.views.control.model.TraceLogLevel)
     */
    @Override
    public void setLogLevel(TraceLogLevel level) {
        fLogLevel = level;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.model.IBaseEventInfo#setLogLevel(java.lang.String)
     */
    @Override
    public void setLogLevel(String levelName) {
        if(TraceLogLevel.TRACE_EMERG.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_EMERG;
        } else if(TraceLogLevel.TRACE_ALERT.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_ALERT;
        } else if(TraceLogLevel.TRACE_CRIT.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_CRIT;
        } else if(TraceLogLevel.TRACE_ERR.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_ERR;
        } else if(TraceLogLevel.TRACE_WARNING.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_WARNING;
        } else if(TraceLogLevel.TRACE_NOTICE.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_NOTICE;
        } else if(TraceLogLevel.TRACE_INFO.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_INFO;
        } else if(TraceLogLevel.TRACE_SYSTEM.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_SYSTEM;
        } else if(TraceLogLevel.TRACE_PROGRAM.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_PROGRAM;
        } else if(TraceLogLevel.TRACE_PROCESS.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_PROCESS;
        } else if(TraceLogLevel.TRACE_MODULE.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_MODULE;
        } else if(TraceLogLevel.TRACE_UNIT.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_UNIT;
        } else if(TraceLogLevel.TRACE_FUNCTION.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_FUNCTION;
        } else if(TraceLogLevel.TRACE_DEFAULT.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_DEFAULT;
        } else if(TraceLogLevel.TRACE_VERBOSE.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_VERBOSE;
        } else if(TraceLogLevel.TRACE_DEBUG.getInName().equals(levelName)) {
            fLogLevel = TraceLogLevel.TRACE_DEBUG;
        } else {
            fLogLevel = TraceLogLevel.TRACE_DEFAULT;
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.model.impl.TraceInfo#hashCode()
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + super.hashCode();
        result = 37 * result + fEventType.ordinal();
        result = 37 * result + fLogLevel.ordinal();
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.model.impl.TraceInfo#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BaseEventInfo)) {
            return false;
        }

        BaseEventInfo otherInfo = (BaseEventInfo) other;
        if (!super.equals(otherInfo)) {
            return false;
        }

        if (fEventType.ordinal() != otherInfo.fEventType.ordinal()) {
            return false;
        }
        
        if (fLogLevel.ordinal() != otherInfo.fLogLevel.ordinal()) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.linuxtools.lttng.ui.views.control.model.impl.TraceInfo#toString()
     */
    @SuppressWarnings("nls")
    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();
            output.append("[BaseEventInfo(");
            output.append(super.toString());
            output.append(",type=");
            output.append(fEventType);
            output.append(",level=");
            output.append(fLogLevel);
            output.append(")]");
            return output.toString();
    }
}