package com.stackroute.helpdesk.userRequest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
<<<<<<< HEAD
=======
import lombok.NoArgsConstructor;
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Ticket")
@Data
@AllArgsConstructor
<<<<<<< HEAD
=======
@NoArgsConstructor
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
public class SuggestionsModel {
    private String id;
    private String suggestion;
}
