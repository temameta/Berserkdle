package org.example.berserkdle.services;

import org.example.berserkdle.dtos.GroupDTO;
import org.example.berserkdle.entities.GroupEntity;
import org.example.berserkdle.repositories.GroupRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService extends AbstractService<GroupDTO, GroupEntity,  GroupRepository> {

    public GroupService(GroupRepository repository) {
        super(repository);
    }

    @Override
    public GroupDTO toDTO(GroupEntity groupEntity) {
        return GroupDTO.builder()
                .name(groupEntity.getName())
                .build();
    }

    @Override
    public GroupEntity toEntity(GroupDTO groupDTO) {
        return GroupEntity.builder()
                .name(groupDTO.getName())
                .build();
    }

    @Cacheable(value = "groupNames", key = "'all'")
    public List<String> getAllNames() {
        return repository.getAllNames();
    }
}
