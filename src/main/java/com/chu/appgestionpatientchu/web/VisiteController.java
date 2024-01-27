
package com.chu.appgestionpatientchu.web;

import com.chu.appgestionpatientchu.domain.Visite;
import com.chu.appgestionpatientchu.dto.VisiteDto;
import com.chu.appgestionpatientchu.services.VisiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visites")
@RequiredArgsConstructor
public class VisiteController {

    @Autowired
    private final VisiteService visiteService;


    @PostMapping
    public VisiteDto saveVisite(@RequestBody VisiteDto visiteDto) {
        return visiteService.saveVisite(visiteDto);
    }

    @GetMapping
    public List<Visite> findAllVisites() {
        return visiteService.findAllVisites();
    }

    @GetMapping("/{id}")
    public Visite findVisiteById(@PathVariable Long id) {
        return visiteService.findVisiteById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteVisite(@PathVariable Long id) {
        visiteService.deleteVisite(id);
    }

    // Uncomment and modify this method if you need to update a Visite
//    @PutMapping("/{id}")
//    public Visite updateVisite(@PathVariable Long id, @RequestBody Visite updatedVisite) {
//        return visiteService.updateVisite(id, updatedVisite);
//    }
}
