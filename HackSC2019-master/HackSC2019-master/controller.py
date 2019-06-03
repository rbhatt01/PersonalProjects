import cv2
    #in charge of camera/keys
from PIL import Image, ImageDraw
    #in charge of drawing boxes/labeling items
from google.cloud import vision
    #Google Vision API

import json

food_list = []



def localize_objects(path):
    #This function takes in the file path as a parameter and localizes the objects
        #1) sends the photo to the cloud
        #2) cloud AI analyzes the photo
        #3) returns a list of objects that are in a dictionary format (similar to a JSON file)
    client = vision.ImageAnnotatorClient()
        #initiates Google Vision API
    with open(path, 'rb') as image_file:
        content = image_file.read()
        #reads file
    image = vision.types.Image(content=content)
        #Specifies that the file type is an image (this could be a video)
    objects = client.object_localization(
        image=image).localized_object_annotations
        #Google Vision API does its magic

    print('Number of objects found: {}'.format(len(objects)))
    for object_ in objects:
        food_list.append(object_.name)
        print(object_.name)
        print('\n{} (confidence: {})'.format(object_.name, object_.score))
        print('Normalized bounding polygon vertices: ')
        for vertex in object_.bounding_poly.normalized_vertices:
            print(' - ({}, {})'.format(vertex.x, vertex.y))
        #Prints the objects in the console based on the localized objects
    highlight_objects(path, objects, "analytics.jpg")
    return objects
        #Returns the list of localized objects


def highlight_objects(input_filename, objects, output_filename):
    #This function is in charge of drawing boxes and labeling localized objects
    im = Image.open(input_filename)
    #Open file
    draw = ImageDraw.Draw(im)
    #Initiate PILLOW
    for obj in objects:
        box = [(vertex.x*im.size[0], vertex.y*im.size[1])
               for vertex in obj.bounding_poly.normalized_vertices]
        #Accesses the vertices in the list of localized objects
        draw.line(box + [box[0]], width=5, fill='#00ff00')
        #Sets the width and color of the box -> draws the box
        draw.text(box[0],
                  str(format(obj.name)),
                  fill='#FF0000')
        #Display the words on top of the box
    im.save(output_filename)
    #Save the new picture to the output file name
    #Create a video capture object


def capture():
    cap = cv2.VideoCapture(0)
    while True:
        ret, frame = cap.read()
        #not sure what this does but i assume it turns on the webcam
        rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2BGRA)
        #Sets the color of the capture from black & white to rgb
        cv2.imshow('Camera', rgb)
        #Window name, window attributes
        if cv2.waitKey(1) & 0xFF == ord('q'):
            #If the key clicked is q, it exits the window and saves it to capture.jpg
            out = cv2.imwrite('capture.jpg', frame)
            break
    cap.release()
    cv2.destroyAllWindows()
    objcts = localize_objects("capture.jpg")
    highlight_objects("capture.jpg", objcts, "analytics.jpg")


#create recipe dictionary based on json file
recipe_list = json.load(open("recipes.json"))


def match_ingredients(availible_foods: list) -> set:

    possible_foods = {}

    for recipe in recipe_list:
        num_matches = 0
        for food in availible_foods:
            found = False
            for ingredient in recipe["ingredients"]:
                if food in ingredient:
                    found = True
            if(found): 
                num_matches += 1

        if num_matches / len(recipe["ingredients"]) >= 0.66:
            possible_foods[recipe["id"]] = (recipe["ingredients"])

    for name, ingredients in possible_foods:
        print(name + ": " + ingredients)

    return possible_foods

if __name__ == '__main__':
    print ("hello")
    capture()
    print ("hello")







