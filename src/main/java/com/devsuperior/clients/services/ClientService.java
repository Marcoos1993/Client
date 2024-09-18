package com.devsuperior.clients.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.clients.dto.ClientDTO;
import com.devsuperior.clients.entities.Client;
import com.devsuperior.clients.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional (readOnly = true)
	public ClientDTO findById(Long id) {
	Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFindException("Id nao encontrado"));
	ClientDTO dto = new ClientDTO(client);
	return dto;
		
	}
	
	@Transactional (readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable){
		Page<Client> result = repository.findAll(pageable);
		
		return result.map(x -> new ClientDTO(x));
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client ();
		CopyDtoToEntity(entity, dto);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		
		try {
			Client entity = repository.getReferenceById(id);
			CopyDtoToEntity(entity, dto);
			entity = repository.save(entity);
			return new ClientDTO(entity);
		}
		catch(ResourceNotFindException e) {
			throw new ResourceNotFindException("Recurso nao encontrado."); 
		}
	}
	
	@Transactional (propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFindException("Recurso nao encontrado.");
		}
		try {
			repository.deleteById(id);
		}
	    catch (DataIntegrityViolationException e) {
	        throw new DatabaseException("Falha de integridade referencial");
	   	}
	}
	
	private void CopyDtoToEntity(Client entity, ClientDTO dto) {
		
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());

	}
}
