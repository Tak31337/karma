#!/usr/bin/env python
import sys

def main():
  if len(sys.argv) < 2:
    print("Hello..")
  query = " ".join(sys.argv[1:])
  print(query)
  
if __name__ == "__main__":
  main()

