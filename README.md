# Project-Faculty-3
Al treilea proiect OOP
In cadrul proiectului am folosit urmatoarele patru design patterns:

    => Factory Method
    => Builder
    => Command
    => Singleton

Am folosit design pattern-ul **Singleton** pentru a evita instantierea multipla in cadrul clasei Database.
Se instantiaza o singura data folosind comanda DataBase.Instance(). Acest design pattern este folosit pentru o singura instanta a clasei, astfel utilizatorii o pot folosi in mod general, plecand de aici, fara sa modifice codul.

Factory Method, am folosit acest tip de design pattern pentru a instantia un obiect de tip persoana, fiind acesta profesor sau student, avand in vedere ca nu doar studentii pot fi vizitatori, ci si profesorii, aceasta asigura instantierea corecta fara prea multe actiuni.

Builder, folosit pentru clasele care au un numar mare de atribute, in special atribute optionale, acesta este util in astfel de circumstante pentru a ne fi mai usor sa construim un obiect, atributele optionale avand valori default (null, 0).

Command, am ales sa folosesc o abordare ce necesita mai multe clase (pentru fiecare comanda introdusa), astfel avand o intelegere mai mare asupra fiecarei comenzi, urmarind-o si memorand-o intr-un istoric. Intr-un astfel de design pattern pot exista si metode care sa anuleze comanda data de un user sau chiar sa se revina cu un pas inapoi, se poate de altfel si continua de unde am ramas prin reexecute(). Am ales sa pastrez totul simplu doar urmarind comenzile.

In alta ordine de idei, pe partea practica am folosit LinkedHashedSet pentru pastrarea ordinii de inserare.
S-au creat noi obiecte doar daca s-au respectat anumite reguli/principii.
