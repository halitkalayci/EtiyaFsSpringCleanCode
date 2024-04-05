package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.dtos.requests.CreateBrandRequest;
import com.etiya.rentacar.business.rules.BrandBusinessRules;
import com.etiya.rentacar.core.utilities.exceptions.types.BusinessException;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperManager;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.BrandRepository;
import com.etiya.rentacar.entities.concretes.Brand;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BrandManagerTest {

  private BrandManager brandManager;
  private BrandRepository brandRepository;

  @BeforeEach
  void setUp(){
    brandRepository = Mockito.mock(BrandRepository.class);
    ModelMapper mapper = new ModelMapper();
    ModelMapperService modelMapperService = new ModelMapperManager(mapper);
    BrandBusinessRules rules = new BrandBusinessRules(brandRepository);
    brandManager = new BrandManager(brandRepository, modelMapperService, rules);

    // File.Open
  }

  @AfterEach
  void tearDown(){
    // File.Close
  }
  @BeforeAll
  static void start(){
    // Bütün testler öncesi 1kez
  }
  @AfterAll
  static void end(){
    // Bütün testler sonrası 1kez
  }

  // TDD => Test Driven Development
  @Test
  void addBrandWithExistingName_ShouldThrowException()
  {
    // 3A Prensipi

    // Arrange => Kullanılacak verileri, fonksiyonları taşıyan classları vs hazırla.
    // Act => Hazırladığım yapıda test edilecek fonk. çalıştırılması
    // Assert => Act'de oluşan durum ile beklenen durum (expected result) uyuşma durumun kontrol eder.

    // Mocking, Mocklama

    Mockito.when(brandRepository.findByNameIgnoreCase("BMW")).thenReturn(Optional.of(new Brand()));

    CreateBrandRequest request = new CreateBrandRequest("BMW");

    assertThrows(BusinessException.class, () -> {
      brandManager.add(request);
    });
  }

  @Test
  void addBrandSuccess()
  {
    Brand savedBrand = new Brand();
    savedBrand.setId(1);
    savedBrand.setName("BMW");
    Mockito.when(brandRepository.findByNameIgnoreCase("BMW")).thenReturn(Optional.empty());
    Mockito.when(brandRepository.save(Mockito.any())).thenReturn(savedBrand);
    CreateBrandRequest request = new CreateBrandRequest("BMW");
    brandManager.add(request);
    assert true;
  }
}
// 3x maliyet
// 15x maliyet