package kasiski;

public class KasiskiVersion2 {
	
	private int l[500];
	private int nods[500];
	private int nl=0;


	private int nodEuclid(int a, int b){
        if (b == 0)
        {
            return a;
        }
        else
        {
            return nodEuclid(b, a % b);
        }
    }



	public int kasiskiAlhoritm()
	{    
	    char *s=(char *)calloc(1,sizeof(char));
	    char fname[100];
	    char c;
	    int n=0,i,j,keylen;
	    FILE *f;
	    printf("file name: ");
	    scanf("%s",fname);
	    f=fopen(fname,"r");
	    if (f==NULL){
	            printf("file not found\n");
	            return 1;
	    }
	    
	    for (i=0;i<500;++i)
	        nods[i]=0;

	    
	    while (!feof(f))
	        if (fread(&c,1,1,f)){     
	            s[n++]=c;              
	            s=(char *)realloc(s,n+1);
	        }

	    s[n]='\0';

	    char str1[4],str2[4];
	    
	    for (i=0;i<strlen(s);++i)
	    {
	        str1[0]=s[i];
	        str1[1]=s[i+1];
	        str1[2]=s[i+2];
	        str1[3]='\0';
	        
	        for (j=i+1;j<strlen(s);++j)
	        {
	            str2[0]=s[j];
	            str2[1]=s[j+1];
	            str2[2]=s[j+2];
	            str2[3]='\0';
	            
	            if (!strcmp(str1,str2)) l[nl++]=j-i;
	            
	        }
	    }
	    
	    for (i=0;i<nl;++i)
	        for (j=i+1;j<nl;++j)
	            nods[nod(l[i],l[j])]++;


	    keylen=0;
	    for (i=2;i<500;++i)
	        if (nods[keylen]<nods[i]) keylen=i;
	        
	    printf("%d\n",keylen);
	    
	    free(s);
	    return 0;
	}
}
