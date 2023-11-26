Maven could not resolve plugins: e.g  
maven-compiler-plugin.  
Solved:   
```
mvn clean install -U
```
-U Option: 
>Forces a check for missing
releases and updated snapshots on
remote repositories.
---
git had a fatal error:
*"...detected dubious ownership in repository...".*  
To see the error use command:  
```
git status
```

**Found reason:**  
current user is local windows user,
but owner of the folder (and repository) was the group "Local Administrators",
though the user was in this group, for some reason git did not accept that.

**Solved:**
changed owner of the IdeaProjects folder and all subfolders (where all projects are) to the current local user.