package com.tgl.cmd.appointments.model;

/**
 * Enum representing the status of an appointment.
 * Provides constants to indicate the current state of an appointment.
 */
public enum AppointmentStatus {

    /**
     * Indicates that the appointment is scheduled and active.
     */
    SCHEDULED,

    /**
     * Indicates that the appointment has been canceled.
     */
    CANCELLED,

    /**
     * Indicates that the appointment is closed and completed.
     */
    CLOSED;
}
