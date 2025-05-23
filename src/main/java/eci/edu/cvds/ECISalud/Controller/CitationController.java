package eci.edu.cvds.ECISalud.Controller;

import eci.edu.cvds.ECISalud.Entitity.Citation;
import eci.edu.cvds.ECISalud.Service.CitationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/citation")
public class CitationController {
    @Autowired
    CitationService citationService;

    @Operation(summary = "Agendar una cita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita creada exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error al crear la cita", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Map<String,String>> createCitation(
            @Parameter(
            description = "Datos de la cita a crear",
            required = true)
            @RequestBody Citation citation
        ) {
        try {
            boolean isPosible = citationService.programate(
                    citation.getDate(), citation.getSpecialization(), citation.getDoctor(), citation.getLocation(), citation.getUser(), citation.getEmail(), citation.getUserId());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Cita creada exitosamente");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al crear el turno: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "Obtiene todas las citas de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Citas obtenidas con exito"),
            @ApiResponse(responseCode = "500", description = "Error al obtener las citas del usuario", content = @Content)
    })
    @GetMapping("/list")
    public ResponseEntity<?> getCites(
            @Parameter(
                    description = "Correo para ver el usuario y estado",
                    required = true
            )
            @RequestBody Citation citation

    ) {
        try {
            Optional<Citation> citations = citationService.listCitation(citation.getEmail(), citation.getState());
            return new ResponseEntity<>(citations, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al obtener la lista de turnos: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Cancela una cita del usurio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita cancelada con exito"),
            @ApiResponse(responseCode = "500", description = "Error al cancelar la cita del usuario", content = @Content)
    })
    @PutMapping("Disable")
    public ResponseEntity<?> getCites(
            @Parameter(
                    description = "Estado para cancelar la cita",
                    required = true
            )
            @RequestParam String Id

    ) {
        try{
            citationService.cancell(Id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Turno cancelado exitosamente");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al pasar el turno: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


