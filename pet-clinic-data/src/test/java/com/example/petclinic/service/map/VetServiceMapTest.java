package com.example.petclinic.service.map;

//class VetServiceMapTest {
//    VetServiceMap vetServiceMap;
//
//    Long id = 1L;
//
//    @BeforeEach
//    void setUp() {
//        vetServiceMap = new VetServiceMap(new SpecialityServiceMap());
//        Vet vet = Vet.builder().id(id).build();
//
//        vetServiceMap.save(vet);
//    }
//
//    @Test
//    void findAll() {
//        Set<Vet> vetSet = vetServiceMap.findAll();
//
//        assertEquals(1, vetSet.size());
//    }
//
//    @Test
//    void deleteById() {
//        vetServiceMap.deleteById(id);
//
//        assertEquals(0, vetServiceMap.findAll().size());
//    }
//
//    @Test
//    void delete() {
//        vetServiceMap.delete(vetServiceMap.findById(id));
//
//        assertEquals(0, vetServiceMap.findAll().size());
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void save() {
//    }
//}