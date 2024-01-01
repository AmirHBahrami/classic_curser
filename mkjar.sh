mkdir -p jar
touch jar/manifest.txt
echo "Main-Class: Main" > jar/manifest.txt
cd bin
jar cvfm ../jar/CursedJar.jar ../jar/manifest.txt *
