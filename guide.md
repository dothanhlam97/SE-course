### how to set up localhost in ubuntu

Maven setup 

markdown:/home/lam/SE-course/guide.md.rendered?file%3A%2F%2F%2Fhome%2Flam%2FSE-course%2Fguide.md


mvn
mvn archetype:generate

mvn clean install 
cd <name folder> 
mvn clean install 
mvn clean install dependency:copy-dependencies
java -cp "target/dependency/*:target/testapp-0.1.jar" com.bioturing.lam.App
echo -n "/homt/lam/SE-course/testapp/src/main/java/com/bioturing/lam/App.java" > run.sh
cat run.sh
echo -n 'java -cp "target/dependency/*:target/testapp-0.1.jar" com.bioturing.lam.App' > run.sj 
cat run.sh 
sh run.sh
sh build.sh
cat ~./bash_history
