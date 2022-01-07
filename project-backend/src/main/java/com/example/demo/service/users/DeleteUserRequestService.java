package com.example.demo.service.users;

import com.example.demo.model.users.DeleteUserRequest;
import com.example.demo.repository.users.DeleteUserRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserRequestService {

    private DeleteUserRequestRepository deleteUserRequestRepository;

    public DeleteUserRequestService(DeleteUserRequestRepository deleteUserRequestRepository){
        this.deleteUserRequestRepository = deleteUserRequestRepository;
    }

    public DeleteUserRequest saveRequest(DeleteUserRequest request){
        return this.deleteUserRequestRepository.save(request);
    }

}
