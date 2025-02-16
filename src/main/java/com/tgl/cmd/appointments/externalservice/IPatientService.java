package com.tgl.cmd.appointments.externalservice;

import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
import com.tgl.cmd.appointments.model.Appointment;
import com.tgl.cmd.appointments.model.Patient;

/**
 * Interface for Patient Service.
 */
public interface IPatientService {

    /**
     * Method to get patient status from patient API.
     *
     * @param appointment The appointment object containing patient information.
     * @return True if the patient is active, otherwise false.
     */
    boolean getPatientStatusFromPatientApi(String patientId);
    Patient getPatientById(String patientId);
}
