---
layout: default
title: Vishnu's Index Page
---

## Hey there!

I'm Vishnu Aravind, a Sacramento Kings fan and I like biology. In terms of CS, my favorite topic is ML.

- Here is a link to my [github](https://github.com/VishnuAravind12).


# Schedule:

|    Class     | Teacher  | Period |
|--------------|----------|--------|
| AP Stats     | Jenkins  | 1      |
| AP Gov       | Coats    | 2      |
| AP CSA       | Mort     | 3      |
| AP E and M   | Millman  | 4      |
| Off Roll     | N/A      | 5      |


# The Timebox

<table style="width:100%; border-collapse: collapse; border: 1px solid #ddd;">
    <thead>
        <tr style="background-color: #f2f2f2;">
            <th>Week</th>
            <th>Plans</th>
            <th>Hacks(Todo)</th>
            <th>Tangibles</th>
            <th>Review Ticket/Issues</th>
        </tr>
    </thead>
    <tbody>
        {% for row in site.data.labnotebook %}
        <tr>
            <td>{{ row.week }}</td>
            <td>
                {% for plan in row.plans %}
                - {{ plan }}<br>
                {% endfor %}
            </td>
            <td>
                {% for hack in row.hacks %}
                - {{ hack }}<br>
                {% endfor %}
            </td>
            <td>
                {% for tangible in row.tangibles %}
                <a href="{{ tangible.url }}">{{ tangible.text }}</a><br>
                {% endfor %}
            </td>
            <td>
                {% if row.review[0].url %}
                    {% for review_item in row.review %}
                    <a href="{{ review_item.url }}">{{ review_item.text }}</a><br>
                    {% endfor %}
                {% else %}
                    {{ row.review }}
                {% endif %}
            </td>
        </tr>
        {% endfor %}
    </tbody>
</table>





<details>
  <summary>Click to see my freeform picture and an explanation!</summary>

  <img src="{{ site.baseurl }}/images/newimage.png" alt="My freeform picture">

  <br>
  <br>

  Just started with GitHub Pages and learned how to use Markdown. Here's an image that depicts who I am as a person. I have a passion for basketball, and I love to get in the gym and work on my vertical. I also love AI and computational biology. But family is definitely the most important thing for me.
  
  <br>
</details>