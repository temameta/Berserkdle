package org.example.berserkdle.services;

import org.example.berserkdle.dtos.GroupDTO;
import org.example.berserkdle.entities.GroupEntity;
import org.example.berserkdle.repositories.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends AbstractService<GroupDTO, GroupEntity,  GroupRepository> {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        super(groupRepository);
        this.groupRepository = groupRepository;
    }

    @Override
    public GroupDTO findByName(String name) {
        return toDTO(groupRepository.findByName(name));
    }

    @Override
    public GroupDTO toDTO(GroupEntity groupEntity) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setName(groupEntity.getName());
        groupDTO.setId(groupEntity.getId());
        return groupDTO;
    }

    @Override
    public GroupEntity toEntity(GroupDTO groupDTO) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setName(groupDTO.getName());
        groupEntity.setId(groupDTO.getId());
        return groupEntity;
    }
}
