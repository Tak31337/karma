#!/usr/bin/env python
import sys

for arg in sys.argv:
    if arg != 'plugins/test.py':
        print(arg)
    
print("Hello there.")