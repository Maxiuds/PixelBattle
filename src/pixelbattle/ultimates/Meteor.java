package pixelbattle.ultimates;

import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

/** Масовий магічний удар, що завдає випадкової шкоди кожному ворогу. */
public class Meteor implements Ultimate {
    @Override
    public String getName() { return "Метеорит"; }

    @Override
    public String getDescription() { return "Завдає кожному живому ворогу 20–30 шкоди"; }

    @Override
    public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
        int totalDamage = 0;
        int hitEnemies = 0;
        for (Fighter enemy : enemies) {
            if (enemy.isAlive()) {
                totalDamage += enemy.takeDamage(Fighter.random(20, 30));
                hitEnemies++;
            }
        }
        return user.getName() + " викликає Метеорит: " + totalDamage
                + " шкоди по " + hitEnemies + " ворогах";
    }
}
