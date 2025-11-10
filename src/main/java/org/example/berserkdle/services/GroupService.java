package org.example.berserkdle.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.berserkdle.dtos.GroupDTO;
import org.example.berserkdle.entities.GroupEntity;
import org.example.berserkdle.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public List<GroupDTO> findAll() {
        return toGroupDTO(groupRepository.findAll());
    }

    public GroupDTO findById(Long id) {
        return toGroupDTO(groupRepository.findById(id));
    }

    public GroupDTO findByName(String name) {
        return toGroupDTO(groupRepository.findByName(name));
    }

    public GroupDTO toGroupDTO(GroupEntity groupEntity) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(groupEntity.getId());
        groupDTO.setName(groupEntity.getName());
        return groupDTO;
    }

    public List<GroupDTO> toGroupDTO(List<GroupEntity> groupEntities) {
        List<GroupDTO> groupDTOs = new ArrayList<>();
        for (GroupEntity groupEntity : groupEntities) {
            groupDTOs.add(toGroupDTO(groupEntity));
        }
        return groupDTOs;
    }

    public GroupDTO toGroupDTO(Optional<GroupEntity> optionalGroupEntity) {
        if (optionalGroupEntity.isPresent()) {
            return toGroupDTO(optionalGroupEntity.get());
        }
        return null;
    }
}
