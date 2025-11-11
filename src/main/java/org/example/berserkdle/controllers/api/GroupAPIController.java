package org.example.berserkdle.controllers.api;

import org.example.berserkdle.dtos.GroupDTO;
import org.example.berserkdle.entities.GroupEntity;
import org.example.berserkdle.services.GroupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group")
public class GroupAPIController extends AbstractAPIController<GroupDTO, GroupEntity, GroupService> {

    public GroupAPIController(GroupService service) {
        super(service);
    }
}
