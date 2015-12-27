package io.puharesource.osrs.eventsystem.events;

import io.puharesource.osrs.eventsystem.Event;
import org.osbot.rs07.api.ui.Skill;

/**
 * The skill event is an event that should handle
 * anything to do with skills.
 */
public abstract class SkillEvent extends Event {
    protected final Skill skill;

    public SkillEvent(final Skill skill) {
        if (skill == null) throw new IllegalArgumentException("skill cannot be null!");

        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }
}
