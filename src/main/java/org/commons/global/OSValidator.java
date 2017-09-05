package org.commons.global;
import org.apache.commons.lang3.SystemUtils;
import org.commons.models.OSType;

public class OSValidator {

	public static OSType osType (){
		return getOsType();
	}
	
	public static boolean isWindows() {
		return SystemUtils.IS_OS_WINDOWS;
	}
 
	public static boolean isMac() {
		return SystemUtils.IS_OS_MAC;
	}
 
	public static boolean isLinux() {
		return SystemUtils.IS_OS_LINUX;
	}
	
	public static boolean isSolaris(){
		return SystemUtils.IS_OS_SOLARIS;
	}
	
	public static boolean isSunOs(){
		return SystemUtils.IS_OS_SUN_OS;
	}
	
	public static boolean isUnix(){
		return SystemUtils.IS_OS_UNIX;
	}
	
	public static String osArch(){
		return SystemUtils.OS_ARCH;
	}
	
	
	public static String osName(){
		return SystemUtils.OS_NAME ;
	}
	
	
	public static String osLanguage(){
		return SystemUtils.USER_LANGUAGE ;
	}
		
	private static OSType  getOsType(){
		
		if(isWindows()){
			return OSType.WINDOWS;
		}else if(isMac()){
			return OSType.LINUX;
		}else if(isLinux()){
			return OSType.MACOSX;
		}else if(isSolaris()){
			return OSType.SOLARIS;
		}else if(isSunOs()){
			return OSType.SUNOS;
		}else if(isUnix()){
			return OSType.UNIX;
		}else{
			return OSType.OSNOTSUPPORTED;
		}
			
	}
}
