package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< Updated upstream
=======
@Role(Role.Type.EVENT)
@Expires("30m")
>>>>>>> Stashed changes
public class Event {

    private String lane;
    private Champion mainRole;
    private Champion secondRole;
    private EventType eventType;
}
