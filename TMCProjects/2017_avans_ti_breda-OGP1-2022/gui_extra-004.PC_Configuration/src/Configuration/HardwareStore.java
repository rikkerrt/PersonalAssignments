/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Etienne
 */
public class HardwareStore {
    
    public static List<String> GetCpuList() {
        ArrayList<String> cpuList = new ArrayList<>();
        cpuList.add("Intel Pentium G4560");
        cpuList.add("Intel Celeron G3930");
        cpuList.add("Intel Core i5 9600K");
        cpuList.add("Intel Core i7 9700K");
        cpuList.add("Intel Core i9 9900K");
        cpuList.add("Intel Core i3 7100");
        
        return cpuList;
    }
    
    
    public static List<String> GetGpuList() {
        ArrayList<String> gpuList = new ArrayList<>();
        gpuList.add("GeForce GTX 1070 Ti");
        gpuList.add("GeForce RTX 2070");
        gpuList.add("GeForce GT 710");
        
        return gpuList;
    }
    
    public static List<String> GetMemoryList() {
        ArrayList<String> memoryList = new ArrayList<>();
        memoryList.add("8GB DDR4");
        memoryList.add("16GB DDR4");
        memoryList.add("32GB DDR4");
        memoryList.add("64GB DDR4");
        memoryList.add("128GB DDR4");
        
        return memoryList;
    }
}
