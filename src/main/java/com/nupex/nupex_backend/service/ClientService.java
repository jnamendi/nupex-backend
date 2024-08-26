package com.nupex.nupex_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nupex.nupex_backend.dto.ClientDTO;
import com.nupex.nupex_backend.mapper.ClientMapper;
import com.nupex.nupex_backend.model.Client;
import com.nupex.nupex_backend.repository.ClientRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
	private final ClientRepository clientRepository;
	private final ClientMapper clientMapper;

    public List<ClientDTO> getAllClients(){
    	List<Client> clients = clientRepository.findAll();
        return clients.stream().map(clientMapper::toDTO).collect( Collectors.toList() );
    }
    
    public ClientDTO getClientById(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()){
            return clientMapper.toDTO(optionalClient.get());        	
        }
        log.info("Client with id: {} doesn't exist", id);
        return null;
    }
    
    
    public ClientDTO saveClient(ClientDTO clientDto){
        Client savedClient =  clientMapper.toEntity(clientDto);
        savedClient = clientRepository.save(savedClient);
        log.info("Employee with id: {} saved successfully", savedClient.getId());
        clientDto.setId(savedClient.getId());
        return clientDto;
    }
    
    public ClientDTO updateClient(ClientDTO clientDto){
    	Optional<Client> existingClient = clientRepository.findById(clientDto.getId());
        if(existingClient.isPresent()) {
            Client client = existingClient.get();
            clientMapper.updateEntityFromDTO(clientDto, client);
            clientRepository.save(client);
            log.info("Employee with id: {} updated successfully", client.getId());
            clientDto.setId(client.getId());
        }
        return clientDto;
    }
}
