using System;
using System.Reflection;

namespace OSiSP_lab1
{
    class Program
    {
        static void PrintAllPublicMethods(string className) {
    		Console.WriteLine("Methods in "+className+" class:");
            Type myType = Type.GetType("OSiSP_lab1."+className, false, true);

			MethodInfo[] methods = myType.GetMethods ();

			for (int i=0; i<methods.Length; i++)
            {
                Console.Write(" "+i+"->"+methods[i].Name);
                Console.WriteLine(convertParametersListToString(methods[i]));
            }
        }

    	static string convertParametersListToString(MethodInfo method){
    			ParameterInfo[] parameters = method.GetParameters();
                string parametersString ="(";
                foreach (ParameterInfo param in parameters)
                {
                    parametersString+=param.ParameterType.Name + " "+param.Name+", ";
                }
                parametersString= parametersString.TrimEnd(new char[]{' ',','});
                parametersString+=")";
               return parametersString;
    	}
    	
    	static string convertParametersListToString(ConstructorInfo constructor){
    			ParameterInfo[] parameters = constructor.GetParameters();
                string parametersString ="";
                foreach (ParameterInfo param in parameters)
                {
                    parametersString+=param.ParameterType.Name + " "+param.Name+", ";
                }
               return parametersString.TrimEnd(new char[]{' ',','});
    	}
    	
    	
    	static object[] inputParameters(ParameterInfo[] parameters){
    		object[] param = new object[parameters.Length];
    		for (int i = 0; i < parameters.Length; i++)
            {
            	Console.Write("Please enter "+parameters[i].ParameterType.Name+" parameter > ");
            	try{
                	param[i] = Convert.ChangeType(Console.ReadLine(), Type.GetType("System."+parameters[i].ParameterType.Name, false, true));
            	}catch(FormatException){
            		Console.WriteLine("Incorrect parameter type");
            		i--;
            	}

            }
    		return param;
    	}


        static void Main(string[] args)
        {
        	string className = "IntegerUtil";
            PrintAllPublicMethods(className);

            int methodNumber = Int32.Parse(Console.ReadLine());
            Console.WriteLine(CallMethods(methodNumber, className));
            Console.ReadLine();
        }

        static object CallMethods(int methodNumber, String className)
        {
            Type myType = Type.GetType("OSiSP_lab1."+className, false, true);
            MethodInfo[] methods = myType.GetMethods();

            ParameterInfo[] parameters = methods[methodNumber].GetParameters();
            
            Console.Write(methods[methodNumber].Name);
            Console.WriteLine(convertParametersListToString(methods[methodNumber]));
            
            object[] param = inputParameters(parameters);

                    
        	if(methods[methodNumber].IsStatic){
        		return methods[methodNumber].Invoke(null,param);
        	}
        	else{
            	ConstructorInfo conct = myType.GetConstructors()[0];
        		string conctParam = convertParametersListToString(conct);
        		if(!conctParam.Equals("")){
        			Console.WriteLine("Constructor parameters: "+conctParam);
        		}
        	
        		ParameterInfo[] conctParameters = conct.GetParameters();
        		object[] conctParam1 = inputParameters(conctParameters);
        		
            	var dllClass = conct.Invoke(conctParam1);
            	return methods[methodNumber].Invoke(dllClass,param);
        	}
        }
    }
}
