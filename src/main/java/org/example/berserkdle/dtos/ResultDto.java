package org.example.berserkdle.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultDto {
    private RequestedPersonDto requestedPersonDto;
    private MatchStatus genderMatch;
    private MatchStatus groupMatch;
    private MatchStatus speciesMatch;
    private MatchStatus weaponMatch;
    private MatchStatus arcMatch;

    public enum MatchStatus {
        CORRECT,
        PARTIAL,
        WRONG
    }

    public boolean isFullyCorrect() {
        return genderMatch == MatchStatus.CORRECT &&
                groupMatch == MatchStatus.CORRECT &&
                speciesMatch == MatchStatus.CORRECT &&
                weaponMatch == MatchStatus.CORRECT &&
                arcMatch == MatchStatus.CORRECT;
    }
}
