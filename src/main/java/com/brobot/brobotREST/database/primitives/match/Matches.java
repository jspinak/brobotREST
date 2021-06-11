package com.brobot.brobotREST.database.primitives.match;

import com.brobot.brobotREST.database.primitives.region.Region;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import lombok.Data;
import org.sikuli.script.Match;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// simplifies working with collections of MatchObjects
@Data
public class Matches {

    private List<MatchObject> matchObjects = new ArrayList<>();
    private List<MatchObject> nonoverlappingMatches = new ArrayList<>();
    private MatchObject bestMatch = null; // query returns an Optional in case there are no matches
    private List<StateEnum> activeStates = new ArrayList<>();
    private Duration duration;
    private boolean success = false; // for boolean queries (i.e. true for 'find', false for 'vanish' when not empty)
    private List<Region> definedRegions = new ArrayList<>();

    public List<Match> getMatches() {
        return matchObjects.stream()
                .map(MatchObject::getMatch)
                .collect(Collectors.toList());
    }

    public List<Region> getMatchRegions() {
        List<Region> regions = new ArrayList<>();
        matchObjects.forEach(mO -> regions.add(new Region(mO.getMatch())));
        return regions;
    }

    public void add(MatchObject match) {
        matchObjects.add(match);
        addNonoverlappingMatch(match);
        setBestMatch(match);
        addActiveState(match);
    }

    public void addAll(Matches matches) {
        for (MatchObject match : matches.getMatchObjects()) {
            add(match);
        }
    }

    public int size() {
        return matchObjects.size();
    }

    private boolean addNonoverlappingMatch(MatchObject m) {
        Region match = new Region(m.getMatch());
        Region nonoverlap;
        for (MatchObject n : nonoverlappingMatches) {
            nonoverlap = new Region(n.getMatch());
            if (match.overlaps(nonoverlap)) return false;
        }
        nonoverlappingMatches.add(m);
        return true;
    }

    private boolean setBestMatch(MatchObject newMatch) {
        if (bestMatch == null ||
                newMatch.getMatch().getScore() > bestMatch.getMatch().getScore()) {
            bestMatch = newMatch;
            return true;
        }
        return false;
    }

    private boolean addActiveState(MatchObject newMatch) {
        activeStates.add(newMatch.getState());
        return true;
    }

    public Optional<MatchObject> getBestMatch() {
        return Optional.ofNullable(bestMatch);
    }

    public List<StateEnum> getActiveStates() {
        return activeStates;
    }

    public boolean isEmpty() {
        return matchObjects.isEmpty();
    }

    public String getText() {
        StringBuilder text = new StringBuilder();
        for (MatchObject matchObject : matchObjects) {
            text.append(matchObject.getText());
            if (matchObject != matchObjects.get(matchObjects.size()-1)) text.append("\n");
        }
        return text.toString();
    }

    public Region getDefinedRegion() {
        if (definedRegions.isEmpty()) return new Region();
        return definedRegions.get(0);
    }
}
