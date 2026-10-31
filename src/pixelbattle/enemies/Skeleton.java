package pixelbattle.enemies;

import pixelbattle.core.Enemy;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;

/** Скелет, який один раз воскресає після смертельного удару. */
public class Skeleton extends Enemy {
    private boolean resurrectionUsed;

    public Skeleton() {
        super("Скелет", 50, new Sprite("skelet_idle_anim", 4, 6));

        addSkill(new Skill("Кістяний удар", Target.ENEMY, 0, (user, target) ->
                user.getName() + " б'є кісткою " + target.getName() + ": "
                        + target.takeDamage(random(8, 12)) + " шкоди"));
    }

    @Override
    protected int modifyIncomingDamage(int damage) {
        if (!resurrectionUsed && damage >= getHp()) {
            resurrectionUsed = true;
            return Math.max(0, getHp() - 1);
        }
        return damage;
    }
}
