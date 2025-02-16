//
//
//
//package com.tg.cmd.test;
//
//import com.tg.cmd.clinic.dto.ClinicDTO;
//import com.tg.cmd.clinic.mappers.ClinicMappers;
//import com.tg.cmd.clinic.model.Clinic;
//import com.tg.cmd.clinic.repository.ServiceRepository;
//import com.tg.cmd.clinic.service.ClinicServiceImpl;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ClinicServiceImplTest {
//
//    @Mock
//    private ServiceRepository serviceRepository;
//
//    @Mock
//    private ClinicMappers clinicMapper;
//
//    @InjectMocks
//    private ClinicServiceImpl clinicService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAddClinic_ValidClinic_ShouldSaveSuccessfully() {
//        Clinic clinic = new Clinic();
//        clinic.setName("Healthy Clinic");
//        clinic.setPhoneNumber("1234567890");
//        clinic.setEmail("clinic@example.com");
//        clinic.setStartTime(LocalDateTime.of(2025, 1, 1, 9, 0));
//        clinic.setEndTime(LocalDateTime.of(2025, 1, 1, 17, 0));
//
//        when(serviceRepository.save(clinic)).thenReturn(clinic);
//
//        Clinic savedClinic = clinicService.addClinic(clinic);
//
//        assertNotNull(savedClinic);
//        assertEquals("Healthy Clinic", savedClinic.getName());
//        verify(serviceRepository, times(1)).save(clinic);
//    }
//
//    @Test
//    void testAddClinic_InvalidPhoneNumber_ShouldThrowException() {
//        Clinic clinic = new Clinic();
//        clinic.setName("Invalid Phone Clinic");
//        clinic.setPhoneNumber("12345"); // Invalid phone number
//
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> clinicService.addClinic(clinic));
//        assertEquals("Invalid phone number format. Must be 10 digits.", exception.getMessage());
//        verify(serviceRepository, never()).save(clinic);
//    }
//
//    @Test
//    void testViewAllClinic_ShouldReturnAllClinics() {
//        Clinic clinic1 = new Clinic();
//        clinic1.setId(1L);
//        clinic1.setName("Clinic 1");
//
//        Clinic clinic2 = new Clinic();
//        clinic2.setId(2L);
//        clinic2.setName("Clinic 2");
//
//        List<Clinic> clinics = Arrays.asList(clinic1, clinic2);
//        when(serviceRepository.findAll()).thenReturn(clinics);
//
//        ClinicDTO clinicDTO1 = new ClinicDTO();
//        clinicDTO1.setId(1L);
//        clinicDTO1.setName("Clinic 1");
//
//        ClinicDTO clinicDTO2 = new ClinicDTO();
//        clinicDTO2.setId(2L);
//        clinicDTO2.setName("Clinic 2");
//
//        when(clinicMapper.toClinicDTOList(clinics)).thenReturn(Arrays.asList(clinicDTO1, clinicDTO2));
//
//        List<ClinicDTO> clinicDTOs = clinicService.viewAllClinic();
//
//        assertNotNull(clinicDTOs);
//        assertEquals(2, clinicDTOs.size());
//        verify(serviceRepository, times(1)).findAll();
//        verify(clinicMapper, times(1)).toClinicDTOList(clinics);
//    }
//
//    @Test
//    void testVewClinicId_ClinicExists_ShouldReturnClinic() {
//        Clinic clinic = new Clinic();
//        clinic.setId(1L);
//        clinic.setName("Test Clinic");
//
//        when(serviceRepository.findById(1L)).thenReturn(Optional.of(clinic));
//
//        Clinic foundClinic = clinicService.vewClinicId(1L);
//
//        assertNotNull(foundClinic);
//        assertEquals("Test Clinic", foundClinic.getName());
//        verify(serviceRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void testVewClinicId_ClinicDoesNotExist_ShouldReturnNull() {
//        when(serviceRepository.findById(1L)).thenReturn(Optional.empty());
//
//        Clinic foundClinic = clinicService.vewClinicId(1L);
//
//        assertNull(foundClinic);
//        verify(serviceRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void testUpdateService_ValidData_ShouldUpdateClinic() {
//        Clinic existingClinic = new Clinic();
//        existingClinic.setId(1L);
//        existingClinic.setName("Existing Clinic");
//        existingClinic.setPhoneNumber("1234567890");  // Existing phone number
//
//        Clinic updatedClinic = new Clinic();
//        updatedClinic.setName("Updated Clinic");
//        updatedClinic.setPhoneNumber("0987654321");  // Updated phone number
//
//        when(serviceRepository.findById(1L)).thenReturn(Optional.of(existingClinic));
//        when(serviceRepository.save(existingClinic)).thenReturn(existingClinic);
//
//        // Now, existingClinic will be updated with the values from updatedClinic
//        Clinic result = clinicService.updateService(1L, updatedClinic);
//
//        assertNotNull(result);
//        assertEquals("Updated Clinic", result.getName()); // Check updated name
//        assertEquals("0987654321", result.getPhoneNumber());  // Check updated phone number
//        verify(serviceRepository, times(1)).findById(1L);
//        verify(serviceRepository, times(1)).save(existingClinic);
//    }
//
//
//    @Test
//    void testUpdateService_ClinicNotFound_ShouldThrowException() {
//        // Setup: Clinic with ID 1 does not exist in the repository
//        when(serviceRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Try updating the non-existing clinic and expect an IllegalArgumentException
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            clinicService.updateService(1L, new Clinic());
//        });
//
//        // Validate that the correct error message is returned
//        assertEquals("Clinic with ID 1 not found", exception.getMessage());
//    }
//
//
//    @Test
//    void testDeleteService_ShouldDeleteClinic() {
//        doNothing().when(serviceRepository).deleteById(1L);
//
//        clinicService.deleteService(1L);
//
//        verify(serviceRepository, times(1)).deleteById(1L);
//    }
//}
