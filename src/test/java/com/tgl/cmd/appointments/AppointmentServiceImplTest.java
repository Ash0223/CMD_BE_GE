//package com.tgl.cmd.appointments;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
//import com.tgl.cmd.appointments.exceptions.AppointmentNotFoundException;
//import com.tgl.cmd.appointments.exceptions.DoctorNotAvailableException;
//import com.tgl.cmd.appointments.model.Appointment;
//import com.tgl.cmd.appointments.model.AppointmentStatus;
//import com.tgl.cmd.appointments.model.AppointmentType;
//import com.tgl.cmd.appointments.model.Purpose;
//import com.tgl.cmd.appointments.repository.AppointmentRepository;
//import com.tgl.cmd.appointments.service.AppointmentServiceImpl;
//import com.tgl.cmd.appointments.externalservice.IDoctorService;
//import com.tgl.cmd.appointments.externalservice.IPatientService;
//
//import org.mockito.*;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * Unit tests for {@link AppointmentServiceImpl}.
// *
// * <p>This class contains tests to verify the functionality of appointment scheduling, 
// * retrieval, and cancellation within the {@link AppointmentServiceImpl} service. 
// * It uses Mockito for mocking dependencies and ensures that each scenario is properly tested.
// */
//@RunWith(SpringRunner.class)
//public class AppointmentServiceImplTest {
//
//    @Mock
//    private AppointmentRepository appointmentRepository;
//
//    @Mock
//    private IDoctorService doctorService;
//
//    @Mock
//    private IPatientService patientService;
//
//    @InjectMocks
//    private AppointmentServiceImpl appointmentService;
//
//    private CreateAppointmentDTO appointmentDTO;
//
//    /**
//     * Sets up the test environment by initializing a sample {@link CreateAppointmentDTO}.
//     */
//    @Before
//    public void setUp() {
//        appointmentDTO = new CreateAppointmentDTO();
//        appointmentDTO.getDoctor().setDoctorId("DOC-1234-2024");
//        appointmentDTO.getPatient().setPatientId("PAT-1234-2024");
//        appointmentDTO.setAppointmentDate("2024-12-24");
//        appointmentDTO.setAppointmentTime("10:00");
//        appointmentDTO.setPurposeOfVisit(Purpose.ALLERGY);
//        appointmentDTO.setAppointmentType(AppointmentType.OFFLINE);
//    }
//
//    /**
//     * Tests the successful scheduling of an appointment.
//     * Verifies that the repository's save method is called exactly once.
//     */
//    @Test
//    public void testScheduleAppointment_Success() {
//        // Arrange
//        when(doctorService.getDoctorAvailabilityByDoctorApi("DOC-1234-2024")).thenReturn(true);
//        when(patientService.getPatientStatusFromPatientApi("PAT-1234-2024")).thenReturn(true);
//        when(doctorService.checkDoctorForGivenTimeSlot("2024-12-24", "10:00", "DOC-1234-2024")).thenReturn(true);
//        
//        Appointment mockAppointment = new Appointment();
//        when(appointmentRepository.save(any(Appointment.class))).thenReturn(mockAppointment);
//
//        // Act
//        Appointment result = appointmentService.scheduleAppointment(appointmentDTO);
//
//        // Assert
//        assertNotNull(result);
//        verify(appointmentRepository, times(1)).save(any(Appointment.class));
//    }
//
//    /**
//     * Tests scheduling an appointment when the doctor is not available.
//     * Expects a {@link DoctorNotAvailableException} to be thrown.
//     */
//    @Test(expected = DoctorNotAvailableException.class)
//    public void testScheduleAppointment_DoctorNotAvailable() {
//        // Arrange
//        when(doctorService.getDoctorAvailabilityByDoctorApi("DOC-1234-2024")).thenReturn(false);
//
//        // Act
//        appointmentService.scheduleAppointment(appointmentDTO);
//    }
//
//    /**
//     * Tests scheduling an appointment when the patient is not active.
//     * Expects the result to be null, indicating no appointment was scheduled.
//     */
//    @Test
//    public void testScheduleAppointment_PatientNotActive() {
//        // Arrange
//        when(doctorService.getDoctorAvailabilityByDoctorApi("DOC-1234-2024")).thenReturn(true);
//        when(patientService.getPatientStatusFromPatientApi("PAT-1234-2024")).thenReturn(false);
//
//        // Act
//        Appointment result = appointmentService.scheduleAppointment(appointmentDTO);
//
//        // Assert
//        assertNull(result);
//    }
//
//    /**
//     * Tests scheduling an appointment when the doctor is unavailable in the given time slot.
//     * Expects an {@link AppointmentNotFoundException} to be thrown.
//     */
//    @Test(expected = AppointmentNotFoundException.class)
//    public void testScheduleAppointment_DoctorNotAvailableInSlot() {
//        // Arrange
//        when(doctorService.getDoctorAvailabilityByDoctorApi("DOC-1234-2024")).thenReturn(true);
//        when(patientService.getPatientStatusFromPatientApi("PAT-1234-2024")).thenReturn(true);
//        when(doctorService.checkDoctorForGivenTimeSlot("2024-12-24", "10:00", "DOC-1234-2024")).thenReturn(false);
//
//        // Act
//        appointmentService.scheduleAppointment(appointmentDTO);
//    }
//
//    /**
//     * Tests retrieving an appointment by its ID successfully.
//     * Verifies that the returned appointment has the correct status.
//     */
//    @Test
//    public void testGetAppointmentById_Success() {
//        // Arrange
//        Appointment mockAppointment = new Appointment();
//        mockAppointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);
//        when(appointmentRepository.findById("appointment123")).thenReturn(java.util.Optional.of(mockAppointment));
//
//        // Act
//        Appointment result = appointmentService.getAppointmentById("appointment123");
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(AppointmentStatus.SCHEDULED, result.getAppointmentStatus());
//    }
//
//    /**
//     * Tests retrieving an appointment by an ID that does not exist.
//     * Expects the result to be null.
//     */
//    @Test
//    public void testGetAppointmentById_NotFound() {
//        // Arrange
//        when(appointmentRepository.findById("appointment123")).thenReturn(java.util.Optional.empty());
//
//        // Act
//        Appointment result = appointmentService.getAppointmentById("appointment123");
//
//        // Assert
//        assertNull(result);
//    }
//
//    /**
//     * Tests successfully canceling an existing appointment.
//     * Verifies that the appointment status is updated to {@link AppointmentStatus#CANCELLED}.
//     */
//    @Test
//    public void testCancelAppointment_Success() {
//        // Arrange
//        Appointment mockAppointment = new Appointment();
//        mockAppointment.setAppointmentStatus(AppointmentStatus.SCHEDULED);
//        when(appointmentRepository.findById("appointment123")).thenReturn(java.util.Optional.of(mockAppointment));
//        when(appointmentRepository.save(any(Appointment.class))).thenReturn(mockAppointment);
//
//        // Act
//        Appointment result = appointmentService.cancelAppointment("appointment123");
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(AppointmentStatus.CANCELLED, result.getAppointmentStatus());
//    }
//
//    /**
//     * Tests canceling an appointment by an ID that does not exist.
//     * Expects an {@link AppointmentNotFoundException} to be thrown.
//     */
//    @Test(expected = AppointmentNotFoundException.class)
//    public void testCancelAppointment_NotFound() {
//        // Arrange
//        when(appointmentRepository.findById("appointment123")).thenReturn(java.util.Optional.empty());
//
//        // Act
//        appointmentService.cancelAppointment("appointment123");
//    }
//}
