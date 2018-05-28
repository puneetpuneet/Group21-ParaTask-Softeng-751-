package com.uni.group21;//####[1]####
//####[1]####
import java.io.File;//####[3]####
import pt.runtime.TaskIDGroup;//####[4]####
//####[4]####
//-- ParaTask related imports//####[4]####
import pt.runtime.*;//####[4]####
import java.util.concurrent.ExecutionException;//####[4]####
import java.util.concurrent.locks.*;//####[4]####
import java.lang.reflect.*;//####[4]####
import pt.runtime.GuiThread;//####[4]####
import java.util.concurrent.BlockingQueue;//####[4]####
import java.util.ArrayList;//####[4]####
import java.util.List;//####[4]####
//####[4]####
public class ParaTaskImp {//####[6]####
    static{ParaTask.init();}//####[6]####
    /*  ParaTask helper method to access private/protected slots *///####[6]####
    public void __pt__accessPrivateSlot(Method m, Object instance, TaskID arg, Object interResult ) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {//####[6]####
        if (m.getParameterTypes().length == 0)//####[6]####
            m.invoke(instance);//####[6]####
        else if ((m.getParameterTypes().length == 1))//####[6]####
            m.invoke(instance, arg);//####[6]####
        else //####[6]####
            m.invoke(instance, arg, interResult);//####[6]####
    }//####[6]####
//####[8]####
    private long size;//####[8]####
//####[9]####
    private String path;//####[9]####
//####[11]####
    public void init() {//####[11]####
        path = new Constant().path;//####[12]####
        long start = System.nanoTime();//####[14]####
        System.out.println("Start time is :  " + start);//####[15]####
        calDriveSize(path);//####[17]####
        long end = System.nanoTime();//####[19]####
        System.out.println("End time is :  " + end);//####[20]####
        long nanoseconds = (end - start) / 1000000000;//####[22]####
        System.out.println("Total time taken   : " + nanoseconds + "  seconds");//####[23]####
        System.out.println("Size of the file is :   " + size + "  bytes");//####[25]####
    }//####[26]####
//####[28]####
    private void calDriveSize(String path) {//####[28]####
        File drive = new File(path);//####[29]####
        File[] files = drive.listFiles();//####[30]####
        for (File childFile : files) //####[32]####
        {//####[32]####
            if (childFile.isFile()) //####[33]####
            {//####[33]####
                updateSize(childFile.length());//####[34]####
            } else if (childFile.isDirectory()) //####[35]####
            {//####[35]####
                File[] directoryFiles = childFile.listFiles();//####[36]####
                createTaskGroup(directoryFiles);//####[37]####
            }//####[38]####
        }//####[39]####
    }//####[40]####
//####[42]####
    private static volatile Method __pt__calDirSize_File_method = null;//####[42]####
    private synchronized static void __pt__calDirSize_File_ensureMethodVarSet() {//####[42]####
        if (__pt__calDirSize_File_method == null) {//####[42]####
            try {//####[42]####
                __pt__calDirSize_File_method = ParaTaskHelper.getDeclaredMethod(new ParaTaskHelper.ClassGetter().getCurrentClass(), "__pt__calDirSize", new Class[] {//####[42]####
                    File.class//####[42]####
                });//####[42]####
            } catch (Exception e) {//####[42]####
                e.printStackTrace();//####[42]####
            }//####[42]####
        }//####[42]####
    }//####[42]####
    public TaskID<Void> calDirSize(File dir) {//####[42]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[42]####
        return calDirSize(dir, new TaskInfo());//####[42]####
    }//####[42]####
    public TaskID<Void> calDirSize(File dir, TaskInfo taskinfo) {//####[42]####
        // ensure Method variable is set//####[42]####
        if (__pt__calDirSize_File_method == null) {//####[42]####
            __pt__calDirSize_File_ensureMethodVarSet();//####[42]####
        }//####[42]####
        taskinfo.setParameters(dir);//####[42]####
        taskinfo.setMethod(__pt__calDirSize_File_method);//####[42]####
        taskinfo.setInstance(this);//####[42]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[42]####
    }//####[42]####
    public TaskID<Void> calDirSize(TaskID<File> dir) {//####[42]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[42]####
        return calDirSize(dir, new TaskInfo());//####[42]####
    }//####[42]####
    public TaskID<Void> calDirSize(TaskID<File> dir, TaskInfo taskinfo) {//####[42]####
        // ensure Method variable is set//####[42]####
        if (__pt__calDirSize_File_method == null) {//####[42]####
            __pt__calDirSize_File_ensureMethodVarSet();//####[42]####
        }//####[42]####
        taskinfo.setTaskIdArgIndexes(0);//####[42]####
        taskinfo.addDependsOn(dir);//####[42]####
        taskinfo.setParameters(dir);//####[42]####
        taskinfo.setMethod(__pt__calDirSize_File_method);//####[42]####
        taskinfo.setInstance(this);//####[42]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[42]####
    }//####[42]####
    public TaskID<Void> calDirSize(BlockingQueue<File> dir) {//####[42]####
        //-- execute asynchronously by enqueuing onto the taskpool//####[42]####
        return calDirSize(dir, new TaskInfo());//####[42]####
    }//####[42]####
    public TaskID<Void> calDirSize(BlockingQueue<File> dir, TaskInfo taskinfo) {//####[42]####
        // ensure Method variable is set//####[42]####
        if (__pt__calDirSize_File_method == null) {//####[42]####
            __pt__calDirSize_File_ensureMethodVarSet();//####[42]####
        }//####[42]####
        taskinfo.setQueueArgIndexes(0);//####[42]####
        taskinfo.setIsPipeline(true);//####[42]####
        taskinfo.setParameters(dir);//####[42]####
        taskinfo.setMethod(__pt__calDirSize_File_method);//####[42]####
        taskinfo.setInstance(this);//####[42]####
        return TaskpoolFactory.getTaskpool().enqueue(taskinfo);//####[42]####
    }//####[42]####
    public void __pt__calDirSize(File dir) {//####[42]####
        if (dir != null) //####[43]####
        {//####[43]####
            File[] files = dir.listFiles();//####[44]####
            if (files != null) //####[45]####
            {//####[45]####
                for (File childFile : files) //####[46]####
                {//####[46]####
                    calculateSize(childFile);//####[47]####
                }//####[48]####
            }//####[49]####
        }//####[50]####
    }//####[51]####
//####[51]####
//####[54]####
    public void calculateSize(File dSize) {//####[54]####
        if (dSize.isFile()) //####[55]####
        {//####[55]####
            updateSize(dSize.length());//####[56]####
        } else if (dSize.isDirectory()) //####[57]####
        {//####[57]####
            calDirSize(dSize);//####[58]####
        }//####[59]####
    }//####[60]####
//####[62]####
    private void createTaskGroup(File[] directoryFiles) {//####[62]####
        if (directoryFiles != null) //####[63]####
        {//####[63]####
            int noOfTasks = directoryFiles.length;//####[64]####
            TaskIDGroup<?> group = new TaskIDGroup<TaskID<?>>(noOfTasks);//####[66]####
            for (File childFile : directoryFiles) //####[68]####
            {//####[68]####
                if (childFile.isFile()) //####[69]####
                {//####[69]####
                    updateSize(childFile.length());//####[70]####
                } else if (childFile.isDirectory()) //####[71]####
                {//####[71]####
                    TaskID<?> taskId = calDirSize(childFile);//####[72]####
                    group.add(taskId);//####[73]####
                }//####[74]####
            }//####[75]####
            try {//####[77]####
                group.waitTillFinished();//####[78]####
            } catch (ExecutionException e) {//####[79]####
                e.printStackTrace();//####[80]####
            } catch (InterruptedException e) {//####[81]####
                e.printStackTrace();//####[82]####
            }//####[83]####
        }//####[84]####
    }//####[85]####
//####[87]####
    private synchronized void updateSize(long value) {//####[87]####
        size += value;//####[88]####
    }//####[89]####
}//####[89]####
