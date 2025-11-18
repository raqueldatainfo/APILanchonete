package com.example.lanchoneteAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lanchoneteAPI.model.ClienteModel;
import com.example.lanchoneteAPI.service.ClienteService;


@RestController
@RequestMapping("api/clientes")
public class ClienteController
 {

    @Autowired
    private ClienteService clienteService;
@GetMapping
public ResponseEntity<List<ClienteModel>> listarTodos(ClienteModel cliente){
    //if (Cliente.isDisponivel()){
    List<ClienteModel> Clientes = clienteService.listarTodos();
    return ResponseEntity.ok(Clientes);
   }
  // return null;
//}

@GetMapping("/indisponiveis")
public ResponseEntity<List<ClienteModel>> listarIndisponiveis(ClienteModel Cliente){
    if (!Cliente.isAtivo()){
    List<ClienteModel> Clientes = clienteService.listarTodos();
    return ResponseEntity.ok(Clientes);
    }
    return null;
}

@PostMapping 
public ResponseEntity<ClienteModel> adicionarCliente(@RequestBody ClienteModel Cliente ){
    ClienteModel ClienteSalvo = clienteService.adicionarCliente(Cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteSalvo);
}


@PutMapping("/{id}")
public ResponseEntity<ClienteModel> atualizarCliente(@PathVariable int id,  @RequestBody ClienteModel Cliente){
    ClienteModel ClienteAtualizado = clienteService.atualizarCliente(id, Cliente);
        if(ClienteAtualizado != null){
            return ResponseEntity.ok(ClienteAtualizado);
        }
        return ResponseEntity.notFound().build();
}
}
