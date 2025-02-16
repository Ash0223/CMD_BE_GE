//package com.tg.Doctor;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.tg.Doctor.controller.DoctorController;
//import com.tg.Doctor.dtos.DoctorDTO;
//import com.tg.Doctor.models.Address;
//import com.tg.Doctor.models.Doctor;
//import com.tg.Doctor.models.Experience;
//import com.tg.Doctor.models.ExperienceType;
//import com.tg.Doctor.service.IDoctorService;
//
//class DoctorControllerTest {
//
//    private DoctorController doctorController;
//    private IDoctorService doctorService;
//
//    @BeforeEach
//    void setUp() {
//        doctorService = new IDoctorService() {
//            private final List<Doctor> doctorList = new ArrayList<>();
//
//            @Override
//            public List<Doctor> getAllDoctors() {
//                return new ArrayList<>(doctorList);
//            }
//
//            @Override
//            public Doctor addDoctor(DoctorDTO doctorDTO) {
//                Doctor doctor = new Doctor();
//                doctor.setDoctorId("123"); // Example ID for testing
//                doctor.setDateOfBirth(doctorDTO.getDateOfBirth());
//                doctorList.add(doctor);
//                return doctor;
//            }
//
//            @Override
//            public Doctor viewDoctorId(String doctorId) {
//                return doctorList.stream().filter(doc -> doc.getDoctorId().equals(doctorId)).findFirst().orElse(null);
//            }
//
//            @Override
//            public Doctor updateAddress(String doctorId, Address address) {
//                Doctor doctor = viewDoctorId(doctorId);
//                if (doctor != null) {
//                    doctor.setAddress(address);
//                }
//                return doctor;
//            }
//
//            @Override
//            public void deleteDoctor(String doctorId) {
//                doctorList.removeIf(doc -> doc.getDoctorId().equals(doctorId));
//            }
//
//            @Override
//            public Doctor updateExperience(String doctorId, List<Experience> experiences) {
//                Doctor doctor = viewDoctorId(doctorId);
//                if (doctor != null) {
//                    doctor.setExperiences(experiences);
//                }
//                return doctor;
//            }
//        };
//
//        doctorController = new DoctorController(doctorService);
//    }
//
//    // Positive Test Cases
//    @Test
//    void testAddDoctor_ValidInput() {
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(1980, 1, 1));
//
//        ResponseEntity<?> response = doctorController.addDoctor(doctorDTO);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Data saved successfully", response.getBody());
//    }
//
//    @Test
//    void testUpdateAddress_ValidInput() {
//        String doctorId = "123";
//        Address address = new Address();
//        address.setCity("Test City");
//
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(1980, 1, 1));
//        doctorService.addDoctor(doctorDTO);
//
//        ResponseEntity<?> response = doctorController.updateAddress(doctorId, address);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    void testGetAllDoctors_NonEmptyList() {
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(1980, 1, 1));
//        doctorService.addDoctor(doctorDTO);
//
//        ResponseEntity<?> response = doctorController.getAllDoctors();
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertTrue(response.getBody() instanceof List);
//    }
//
//    @Test
//    void testUpdateExperience_ValidInput() {
//        String doctorId = "123";
//        List<Experience> experiences = List.of(
//            new Experience("Clinic A", 5, "Surgeon", ExperienceType.PRESENTLY_WORKING)
//        );
//
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(1980, 1, 1));
//        doctorService.addDoctor(doctorDTO);
//
//        ResponseEntity<?> response = doctorController.updateExperience(doctorId, experiences);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    void testDeleteDoctor_ValidDoctorId() {
//        String doctorId = "123";
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(1980, 1, 1));
//        doctorService.addDoctor(doctorDTO);
//
//        ResponseEntity<?> response = doctorController.deleteDoctorById(doctorId);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    // Negative Test Cases
//    @Test
//    void testAddDoctor_FutureDateOfBirth() {
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(2050, 1, 1));
//
//        ResponseEntity<?> response = doctorController.addDoctor(doctorDTO);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void testAddDoctor_NullDateOfBirth() {
//        DoctorDTO doctorDTO = new DoctorDTO();
//
//        ResponseEntity<?> response = doctorController.addDoctor(doctorDTO);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void testUpdateAddress_InvalidDoctorId() {
//        Address address = new Address();
//        address.setCity("Test City");
//
//        ResponseEntity<?> response = doctorController.updateAddress("999", address);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void testGetDoctorById_InvalidDoctorId() {
//        ResponseEntity<?> response = doctorController.getDoctorById("999");
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void testUpdateExperience_InvalidDoctorId() {
//        List<Experience> experiences = List.of(
//            new Experience("Clinic A", 5, "Surgeon", ExperienceType.WORKED_IN_PAST)
//        );
//
//        ResponseEntity<?> response = doctorController.updateExperience("999", experiences);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void testUpdateExperience_NullExperienceList() {
//        String doctorId = "123";
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(1980, 1, 1));
//        doctorService.addDoctor(doctorDTO);
//
//        ResponseEntity<?> response = doctorController.updateExperience(doctorId, null);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//    }
//
//    @Test
//    void testUpdateExperience_EmptyExperienceList() {
//        String doctorId = "123";
//        List<Experience> experiences = new ArrayList<>();
//
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(1980, 1, 1));
//        doctorService.addDoctor(doctorDTO);
//
//        ResponseEntity<?> response = doctorController.updateExperience(doctorId, experiences);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//    }
//
//    @Test
//    void testDeleteDoctor_InvalidDoctorId() {
//        ResponseEntity<?> response = doctorController.deleteDoctorById("999");
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    // Edge Test Cases
//    @Test
//    void testAddDoctor_VeryOldDateOfBirth() {
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(1900, 1, 1));
//
//        ResponseEntity<?> response = doctorController.addDoctor(doctorDTO);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Data saved successfully", response.getBody());
//    }
//
//    @Test
//    void testUpdateAddress_NullAddress() {
//        String doctorId = "123";
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(1980, 1, 1));
//        doctorService.addDoctor(doctorDTO);
//
//        ResponseEntity<?> response = doctorController.updateAddress(doctorId, null);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//    }
//
//    @Test
//    void testUpdateExperience_LargeNumberOfExperiences() {
//        String doctorId = "123";
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(1980, 1, 1));
//        doctorService.addDoctor(doctorDTO);
//
//        List<Experience> experiences = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            experiences.add(new Experience("Clinic " + i, i, "Role " + i, ExperienceType.PRESENTLY_WORKING));
//        }
//
//        ResponseEntity<?> response = doctorController.updateExperience(doctorId, experiences);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    void testGetDoctorById_CaseSensitivity() {
//        DoctorDTO doctorDTO = new DoctorDTO();
//        doctorDTO.setDateOfBirth(LocalDate.of(1980, 1, 1));
//        doctorService.addDoctor(doctorDTO);
//
//        ResponseEntity<?> response = doctorController.getDoctorById("ABC");
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void testGetAllDoctors_EmptyList() {
//        ResponseEntity<?> response = doctorController.getAllDoctors();
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertTrue(((List<?>) response.getBody()).isEmpty());
//    }
//}
