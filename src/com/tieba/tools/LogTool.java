package com.tieba.tools;

public class LogTool {
    private String className;

    public static<T> LogTool of(Class<T> clazz) {
        try {
            return new LogTool(clazz.getName());
        } catch(Exception e) {
            System.out.println(
                TimeTool.getCurrentTime()+
                "                            com.tieba.tools.LogTool[ Error ]: 初始化失败\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
                +String.valueOf(e)+
                "\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n"
            );
            return new LogTool("com.tieba.UnknownClass");
        }
    }

    public static<T> LogTool of(T t) {
        try {
            return new LogTool(t.getClass().getName());
        } catch(Exception e) {
            System.out.println(
                TimeTool.getCurrentTime()+
                "                            com.tieba.tools.LogTool[ Error ]: 初始化失败\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
                +String.valueOf(e)+
                "\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n"
            );
            return new LogTool("com.tieba.UnknownClass");
        }
    }

    private LogTool(String str) {
        if(str == null || str.isEmpty()) {
            className = "null";
        } else if(str.length() < 50) {
            StringBuilder sb = new StringBuilder();
            int pre = 50 - str.length();
            for(int i = 0; i < pre; i++) {
                sb.append(" ");
            }
            className = sb.append(str).toString();
        } else {
            className = str;
        }
        System.out.println(TimeTool.getCurrentTime()+"                            com.tieba.tools.LogTool[ Debug ]: 初始化成功 - "+str);
    }

    private void printMessage(String str, String level) {
        System.out.println(TimeTool.getCurrentTime()+" "+className+"[ "+level+" ]: "+str);
    }

    public void debug(String str) {
        this.printMessage(str, "Debug");
    }

    public void info(String str) {
        this.printMessage(str, " Info");
    }

    public void warn(String str) {
        this.printMessage(str, " Warn");
    }

    public void error(String str) {
        this.printMessage(str, "Error");
    }

    public void exception(String str, Exception e) {
        System.out.println(
            new StringBuilder(TimeTool.getCurrentTime())
                .append(" ").append(className)
                .append("[ Error ]: ").append(str)
                .append("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n")
                .append(e)
                .append("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n")
        );
    }
}
