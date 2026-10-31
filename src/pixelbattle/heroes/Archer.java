package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Fighter;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.core.Ultimate;
import pixelbattle.engine.Sprite;

/** Лучниця — швидка й спритна. */
public class Archer extends Hero {

    public Archer() {
        super("Лучниця", 80, new Sprite("elf_f_idle_anim", 4, 4));

        addSkill(new Skill("Постріл", Target.ENEMY, 0, (user, target) ->
                user.getName() + " стріляє в " + target.getName() + ": "
                        + target.takeDamage(random(10, 16)) + " шкоди"));

        addSkill(new Skill("Подвійний постріл", Target.ENEMY, 1, (user, target) -> {
            int firstShot = target.takeDamage(random(7, 11));
            int secondShot = target.takeDamage(random(7, 11));
            return user.getName() + " робить подвійний постріл по " + target.getName()
                    + ": " + (firstShot + secondShot) + " шкоди";
        }));

        setUltimate(new Ultimate() {
            private int arrowCount = 3;

            @Override public String getName() { return "Злива стріл"; }
            @Override public String getDescription() { return "Кожне використання випускає на одну стрілу більше"; }
            @Override public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                int usedArrows = arrowCount++;
                int totalDamage = 0;
                for (Fighter enemy : enemies) {
                    if (enemy.isAlive()) {
                        for (int i = 0; i < usedArrows && enemy.isAlive(); i++) {
                            totalDamage += enemy.takeDamage(Fighter.random(4, 8));
                        }
                    }
                }
                return user.getName() + " випускає зливу з " + usedArrows
                        + " стріл по кожному ворогу: " + totalDamage + " шкоди загалом";
            }
        });
    }

    @Override
    protected int modifyIncomingDamage(int damage) {
        return chance(25) ? 0 : damage;
    }
}
