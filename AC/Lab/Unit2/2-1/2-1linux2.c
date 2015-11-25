int main() 
{
  char *p = (char *)0xFFFFE000;
  *p = 'A';

  return 0;
}
