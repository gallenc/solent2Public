// post generation script archetype-post-generate.groovy based upon 
// https://jar-download.com/artifacts/ng.shoppi/microservice-archetype/1.0.2/source-code/META-INF/archetype-post-generate.groovy
//

println ""
println "running post creation groovy script"
println ""

// println "artifactId: " + artifactId
// println "request is " + request
// println "archetypeArtifactId: " + request.getArchetypeArtifactId()
// println "archetypeGroupId: " + request.getArchetypeGroupId()
// println "archetypeVersion: " + request.getArchetypeVersion()
// println "archetypeName " + request.getArchetypeName()
// println "artifactId " + request.getArtifactId()
// println "groupId " + request.getGroupId()
// println "version " + request.getVersion()

java.io.File file = new java.io.File(artifactId+"/uml")
java.io.File destination = new java.io.File(artifactId +"/"+ artifactId+"-uml" );

if ("basic".equals(artifactId)) {
   println "running archetype-post-generate.groovy project during test artifact creation of 'basic' project."
   println "Not renaming files because test project picks up wrong base directory"
   println "NOT renaming " + file.getAbsolutePath()
   println "          to " + destination.getAbsolutePath()

} else {
   println "running archetype-post-generate.groovy project during artifact creation for" + artifactId
   println "renaming " + file.getAbsolutePath()
   println "      to " + destination.getAbsolutePath()

   if (file.exists()) {
        if (destination.exists()) {
            // Delete the maven generated file
            file.deleteDir();
            throw new RuntimeException("Error: A directory with same name already exists at " + destination.getAbsolutePath())
        }
        file.renameTo(destination)
    }
}

println ""
