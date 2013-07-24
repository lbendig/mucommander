# Mucommander

[muCommander](http://www.mucommander.com) is a lightweight, cross-platform file manager with a dual-pane interface.<br>

## Motivation
Forked from [mucommander-svn](https://svn.mucommander.com), **this modification** replaces the already existing [Hadoop](http://hadoop.apache.org/) [HDFS]
(http://hadoop.apache.org/docs/stable/hdfs_user_guide.html) support (which only supports 0.20.0) and also enables
[Quantcast](https://www.quantcast.com/) File System [(QFS)](https://github.com/quantcast/qfs) browsing.<br>
The patch removes the Hadoop dependency from muCommander's runnable fat JAR, and makes it possible
to provide an arbitrary Hadoop/QFS version via command line parameters. Loading of the classes are done dynamically during 
application startup. If such a dependency is not provided, the application starts without the corresponding file protocol support. 

## Hadoop support (hdfs://)

Provide the location of Hadoop dependencies via the **--hadoop** command line option.

    java -jar mucommander-hadoop-qfs.jar --help
    ...
    --hadoop FILE/FOLDER  HDFS Protocol support: location of Hadoop jar(s) : FILE(core-jar) or FOLDER(as of 0.21)

Note, that as of Hadoop 0.21 the hadoop-core has been split into three subprojects (Common, HDFS, MapReduce), pass
the directory path in this case.

muCommander works with any* Hadoop vesions.<br>
\* Tested on:

    hadoop-0.20.0
    hadoop-0.20.1
    hadoop-0.20.2
    hadoop-0.21.0
    hadoop-0.20.203.0
    hadoop-0.20.204.0
    hadoop-0.20.205.0
    hadoop-0.23.0
    hadoop-0.22.0
    hadoop-1.0.0
    hadoop-0.23.1
    hadoop-1.0.1
    hadoop-1.0.2
    hadoop-1.0.3
    hadoop-2.0.0-alpha
    hadoop-2.0.1-alpha
    hadoop-0.23.3
    hadoop-2.0.2-alpha
    hadoop-1.0.4
    hadoop-1.1.0
    hadoop-0.23.4
    hadoop-0.23.5
    hadoop-1.1.1
    hadoop-0.23.6
    hadoop-2.0.3-alpha
    hadoop-1.1.2
    hadoop-0.23.7 
    hadoop-2.0.4-alpha
    hadoop-1.2.0
    hadoop-0.23.8
    hadoop-2.0.5-alpha
    hadoop-0.23.9

    Cloudera releases: 0.20, 0.23 and 2.0.0

## Quantcast File System (QFS) support (qfs://).

Access to the QFS C++ libraries is done via the [qfs-access](https://github.com/quantcast/qfs/wiki/Developer-Documentation#compiling-java-side) JNI wrapper.<br>
Make sure that the location of the compiled QFS binaries (../qfs/build/release/bin) is in the `LD_LIBRARY_PATH`.
Also provide the location of qfs-access.jar via the **--qfs** command line option:

    java -jar mucommander-hadoop-qfs.jar --help
    ...
    --qfs FILE  QFS Protocol support: location of qfs-access.jar

## Compiling

### mucommander-common-files

    git clone https://github.com/lbendig/mucommander-commons-file.git
    cd mucommander-commons-file
    ant publish # publishes jar to default local repository : ~/.ivy2/local

### mucommander

    git clone https://github.com/lbendig/mucommander.git
    cd mucommander
    cp build_template.properties build.properties
    ant jar

The runnable mucommander-hadoop-qfs.jar  will be created under `mucommander/tmp`

## Examples

    java -jar mucommander-hadoop-qfs.jar --hadoop /home/user/hadoop/hadoop-1.0.0/hadoop-core-1.0.0.jar
    java -jar mucommander-hadoop-qfs.jar --hadoop /home/user/hadoop/hadoop-2.0.0-cdh4.1.2

    export LD_LIBRARY_PATH=/home/user/code/qfs/build/release/lib; \
    java -jar mucommander-hadoop-qfs.jar --hadoop /home/user/hadoop/hadoop-2.0.0-cdh4.1.2 \
    --qfs /home/user/qfs/build/java/qfs-access/qfs-access-1.0.1.jar

## Binary distribution
[mucommander-hadoop-qfs.jar](https://docs.google.com/file/d/0B-nInduBOs0cYkZ0aVdrcWkxSnM/edit?usp=sharing)

## Misc

* Use master branch, svn-sync is just for synchronizing with mucommander-svn
