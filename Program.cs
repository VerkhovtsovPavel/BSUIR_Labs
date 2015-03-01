using System;
using System.Reflection;

namespace OSiSP_lab1
{
    class Program
    {
        static void PrintAllPublicMethods(string className) {
            Type myType = Type.GetType("OSiSP_lab1."+className, false, true);

			MethodInfo[] methods = myType.GetMethods ();

			for (int i=0; i<methods.Length; i++)
            {
                Console.Write(methods[i].Name);
                ParameterInfo[] parameters = methods[i].GetParameters();
                Console.Write("(");
                foreach (ParameterInfo param in parameters)
                {
                    Console.Write(param.ParameterType.Name + " " + param.Name);
                }
                Console.WriteLine(")");
            }
        } 


        static void Main(string[] args)
        {
            PrintAllPublicMethods("DLL");

            string methodName = Console.ReadLine();
            MethodInfo m = checkMethodName(methodName);
            Console.WriteLine(CallMethods(m));
            Console.ReadLine();
        }


        static MethodInfo checkMethodName(string methodName){
            Type myType = Type.GetType("OSiSP_lab1.DLL", false, true);
            MethodInfo[] methods = myType.GetMethods();

            foreach(MethodInfo method in methods){
                if(method.Name.Equals(methodName)){
                    return method;
                }
            }
            return null;
        }

        static object CallMethods(MethodInfo method)
        {
            Type myType = Type.GetType("OSiSP_lab1.DLL", false, true);
            MethodInfo[] methods = myType.GetMethods();

            ParameterInfo[] parameters = method.GetParameters();
            Console.Write("(");
            foreach (ParameterInfo parameter in parameters)
            {
                Console.Write(parameter.ParameterType.Name + " " + parameter.Name);
            }
            Console.WriteLine(")");

            if (parameters.Length != 0)
            {
                Console.WriteLine("Please input methods parameters");
            }

            object[] param = new object[parameters.Length];
            for (int i = 0; i < parameters.Length; i++)
            {
                param[i] = (object)Console.ReadLine();

              /*  Type parameterType = Type.GetType("System."+parameters[i].ParameterType.Name, false, true);

                foreach (MethodInfo m in parameterType.GetMethods())
                {
                    Console.Write(m.Name);
                }*/

            }
            DLL dll = new DLL();
            return method.Invoke(dll,param);
        }
    }
}
