package org.example.berserkdle.services;

import org.example.berserkdle.dtos.GroupDTO;
import org.example.berserkdle.entities.GroupEntity;
import org.example.berserkdle.repositories.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends AbstractService<GroupDTO, GroupEntity,  GroupRepository> {

    public GroupService(GroupRepository repository) {
        super(repository);
    }

    @Override
    public GroupDTO toDTO(GroupEntity groupEntity) {
        return GroupDTO.builder()
                .id(groupEntity.getId())
                .name(groupEntity.getName())
                .build();
    }

    @Override
    public GroupEntity toEntity(GroupDTO groupDTO) {
        return GroupEntity.builder()
                .id(groupDTO.getId())
                .name(groupDTO.getName())
                .build();
    }
}
