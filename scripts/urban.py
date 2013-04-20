#!/usr/bin/env python

import sys
import urllib
import json

class urban_diction:
  def __init__(self):
    self.url = "http://api.urbandictionary.com/v0/define?term="

  def search(self, ser_string):
    search_json = urllib.urlopen(self.url+ser_string)
    search_map = json.loads(search_json.read())
    if search_map["list"] == []:
      print("Computer says no.")
      return
    i=0
    for res in search_map["list"]:
      i += 1
      print(str(i) + ". " + res["definition"])
      if i >= 3:
          return
      
def main():
  if len(sys.argv) < 2:
      print("give me a query.")
  query = " ".join(sys.argv[1:])
  urban = urban_diction()
  urban.search(query)
 
if __name__ == "__main__":
  main()

