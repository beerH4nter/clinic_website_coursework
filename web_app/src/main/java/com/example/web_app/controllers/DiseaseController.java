package com.example.web_app.controllers;

import com.example.web_app.dto.DiseaseDTO;
import com.example.web_app.entity.Disease;
import com.example.web_app.mapper.DiseaseMapper;
import com.example.web_app.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/disease")
public class DiseaseController {
    private final DiseaseService diseaseService;
    private final DiseaseMapper diseaseMapper;

    @Autowired
    public DiseaseController(DiseaseService diseaseService, DiseaseMapper diseaseMapper) {
        this.diseaseService = diseaseService;
        this.diseaseMapper = diseaseMapper;
    }

    @PostMapping("/save")
    public Disease save(@RequestBody @Valid DiseaseDTO diseaseDTO){
        Disease disease = diseaseMapper.toRequest(diseaseDTO);
        diseaseService.save(disease);
        return disease;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllDiseases(){
        try{
            List<Disease> diseases = diseaseService.findAll();
            List<DiseaseDTO> diseaseDTOS = diseases.stream()
                    .map(diseaseMapper::toResponse) // Применяем метод маппера
                    .collect(Collectors.toList());

            return ResponseEntity.ok(diseaseDTOS);
        }catch (ClassCastException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with diseases data");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam int id){
        try{
            Disease disease = diseaseService.findOneById(id);
            DiseaseDTO diseaseDTO = diseaseMapper.toResponse(disease);
            return ResponseEntity.ok(diseaseDTO);
        }catch (ClassCastException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with doctors data");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }
}
