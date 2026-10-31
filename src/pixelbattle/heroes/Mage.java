package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;
import pixelbattle.ultimates.Meteor;

/** Маг — потужні заклинання та лікування. */
public class Mage extends Hero {

    public Mage() {
        super("Маг", 70, new Sprite("wizzard_m_idle_anim", 4, 4));

        addSkill(new Skill("Удар посохом", Target.ENEMY, 0, (user, target) ->
                user.getName() + " б'є посохом " + target.getName() + ": "
                        + target.takeDamage(random(6, 10)) + " шкоди"));

        addSkill(new Skill("Вогняна куля", Target.ENEMY, 1, (user, target) -> {
            int damage = target.takeDamage(random(22, 30));
            return user.getName() + " кидає вогняну кулю в " + target.getName()
                    + ": " + damage + " шкоди";
        }));
        addSkill(new Skill("Зцілення", Target.ALLY, 2, (user, target) -> {
            int healed = target.heal(25);
            return user.getName() + " зцілює " + target.getName() + ": відновлено " + healed + " HP";
        }));

        setUltimate(new Meteor());
    }
}
